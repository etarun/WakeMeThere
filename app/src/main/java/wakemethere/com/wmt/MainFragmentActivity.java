
package wakemethere.com.wmt;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import wakemethere.com.wmt.R;

public class MainFragmentActivity extends FragmentActivity {


    private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private static AlarmListFragment.AlarmListAdapter mAdapter;


	private FragmentTabHost mTabHost;
	private Context context;
	LinearLayout mainLinear;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_fragment);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		Resources res = getResources();

		mTabHost.addTab(mTabHost.newTabSpec("MAPS").setIndicator("MAPS ALARM"),
				GpsMapFragment.class,null);
		
		mTabHost.addTab(mTabHost.newTabSpec("STANDARD").setIndicator("STANDARD ALARM"),
				AlarmListFragment.class,null);

		
		  	
		
	}



    public void deleteAlarm(long id) {


        try {
            final long alarmId = id;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Please confirm")
                    .setTitle("Delete set?")
                    .setCancelable(true)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Cancel Alarms
                            AlarmManagerHelper.cancelAlarms(context);
                            //Delete alarm from DB by id
                            dbHelper.deleteAlarm(alarmId);
                            //Refresh the list of the alarms in the adaptor
                            mAdapter.setAlarms(dbHelper.getAlarms());
                            //Notify the adapter the data has changed
                            mAdapter.notifyDataSetChanged();
                            //Set the
                            AlarmManagerHelper.setAlarms(context);
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
//	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("onResumemain", "onResume");
		
		
	}
	
	




}

