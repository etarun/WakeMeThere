package wakemethere.com.wmt;

/**
 * Created by Tarun on 2/27/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import wakemethere.com.wmt.R;

public class AlarmListFragment extends Fragment implements View.OnClickListener {

    Button btnAddAlarm;
    private AlarmListAdapter mAdapter;
    private Context mcontext;
    private ListView listViewSet;
    private AlarmDBHelper dbHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        v = inflater.inflate(R.layout.standard, container, false);
        btnAddAlarm = (Button) v.findViewById(R.id.buttonAddAlarm);
        btnAddAlarm.setOnClickListener(this);



        mcontext = this.getActivity();
        dbHelper = new AlarmDBHelper(mcontext);
        listViewSet = (ListView) v.findViewById(R.id.list);

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddAlarm: {
               /* Context alarmActivity = getActivity();
                Intent intent;
                intent = new Intent(alarmActivity, AlarmDetailsActivity.class);
                long id = -1;
                intent.putExtra("id", id);
                startActivityForResult(intent, -1);*/
                try{
                    startAlarmDetailsActivity(-1);
                }catch(Exception e){
                    Log.d("TRY Error", "");
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    public void startAlarmDetailsActivity(long id) {
        try{
            Intent intent = new Intent(mcontext, AlarmDetailsActivity.class);
            intent.putExtra("id", id);
            startActivityForResult(intent, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void setAlarmEnabled(long id, boolean isEnabled) {

        AlarmManagerHelper.cancelAlarms(mcontext);

        AlarmModel model = dbHelper.getAlarm(id);
        model.isEnabled = isEnabled;
        dbHelper.updateAlarm(model);

        AlarmManagerHelper.setAlarms(mcontext);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("getElement", ""+dbHelper.getAlarms().size());
        if (resultCode == Activity.RESULT_OK) {

			/*for(int i = 0 ; i<dbHelper.getAlarms().size(); i++){
			Log.d("getElement", ""+mAlarms.toString());
			}
*/			try{
                mAdapter = new AlarmListAdapter(mcontext, dbHelper.getAlarms());
                mAdapter.setAlarms(dbHelper.getAlarms());
                listViewSet.setAdapter(mAdapter);
                //mAdapter.notifyDataSetChanged();
                Log.e("onActivityResult", ""+listViewSet.getCount());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }



    public class AlarmListAdapter extends BaseAdapter {

        private Context mContext;
        private List<AlarmModel> mAlarms;

        public AlarmListAdapter(Context context, List<AlarmModel> alarms) {
            mContext = context;
            mAlarms = alarms;
        }

        public void setAlarms(List<AlarmModel> alarms) {
            mAlarms = alarms;
        }

        @Override
        public int getCount() {
            if (mAlarms != null) {
                return mAlarms.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mAlarms != null) {
                return mAlarms.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            if (mAlarms != null) {
                return mAlarms.get(position).id;
            }
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.alarm_list_item, parent, false);
            }

            AlarmModel model = (AlarmModel) getItem(position);

            TextView txtTime = (TextView) view.findViewById(R.id.alarm_item_time);
            txtTime.setText(String.format("%02d : %02d", model.timeHour, model.timeMinute));

            TextView txtName = (TextView) view.findViewById(R.id.alarm_item_name);
            txtName.setText(model.name);

            updateTextColor((TextView) view.findViewById(R.id.alarm_item_sunday), model.getRepeatingDay(AlarmModel.SUNDAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_monday), model.getRepeatingDay(AlarmModel.MONDAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_tuesday), model.getRepeatingDay(AlarmModel.TUESDAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_wednesday), model.getRepeatingDay(AlarmModel.WEDNESDAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_thursday), model.getRepeatingDay(AlarmModel.THURSDAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_friday), model.getRepeatingDay(AlarmModel.FRDIAY));
            updateTextColor((TextView) view.findViewById(R.id.alarm_item_saturday), model.getRepeatingDay(AlarmModel.SATURDAY));

            ToggleButton btnToggle = (ToggleButton) view.findViewById(R.id.alarm_item_toggle);
            btnToggle.setChecked(model.isEnabled);
            btnToggle.setTag(Long.valueOf(model.id));
            btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setAlarmEnabled(((Long) buttonView.getTag()).longValue(), isChecked);
                }
            });

            view.setTag(Long.valueOf(model.id));
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startAlarmDetailsActivity(((Long) view.getTag()).longValue());
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    ((MainFragmentActivity) mContext).deleteAlarm(((Long) view.getTag()).longValue());
                    return true;
                }
            });

            return view;
        }

        private void updateTextColor(TextView view, boolean isOn) {
            if (isOn) {
                view.setTextColor(Color.GREEN);
            } else {
                view.setTextColor(Color.BLACK);
            }
        }

    }
}