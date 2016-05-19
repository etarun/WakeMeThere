package wakemethere.com.wmt;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class AndroidGlobalClass extends Application implements RingInterface {

    private Context context;
   static MediaPlayer mPlayer  = new MediaPlayer();
    AllDataModel allDataModel = new AllDataModel();

    /* static MediaPlayer mPlayer;*/
    public AndroidGlobalClass(Context context) {

        this.context = context;

    }

    public AndroidGlobalClass() {
        // TODO Auto-generated constructor stub
    }




    public static Uri alarmRingToneText;

    @Override
    public void startRingtone(String ringtoneUri) {
        // Uri uri = Uri.parse(Alconstants.getRingtoneUri(context));

        Uri uri = Uri.parse(ringtoneUri);
        Log.e("RingtoneUri in global class",""+ uri);
        //Log.e("RingtoneUri in global class",""+ Alconstants.ringtoneUri);


        try {

            if (uri != null) {


                mPlayer.setDataSource(context, uri);
                mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                //  mPlayer.setLooping(true);
                mPlayer.prepare();
                mPlayer.start();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.e("start", "start");
    }


    @Override
    public void stopRingtone() {
        try {


            Log.e("stopRingtone()", "");



            try {


                mPlayer.stop();
                mPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

//for activity
//MyApp appState = ((MyApp)getApplicationContext());
//String state = appState.getState();