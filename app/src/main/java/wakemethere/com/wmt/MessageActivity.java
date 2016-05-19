package wakemethere.com.wmt;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import wakemethere.com.wmt.R;

public class MessageActivity extends Activity {

    private static Context context;
    AndroidGlobalClass androidGlobalClassObject;
//static Context mcontext;
   static MediaPlayer mPlayer;




    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

        context=this;


        /*stopRingTone();
        finish();*/

        try {



          AndroidGlobalClass androidGlobalClass= new AndroidGlobalClass(getApplicationContext());
            androidGlobalClass.stopRingtone();
        } catch (Exception e) {

            e.printStackTrace();
        }
	}



 /*   public static void startRingTone(Context context){


       // this.context=context;
        Log.e("RingtoneUri in global class", "" + Alconstants.ringtoneUri);




        *//*Ringtone ringtone = RingtoneManager.getRingtone(context, Alconstants.ringtoneUri);
        ringtone.setStreamType(AudioManager.STREAM_ALARM);
        ringtone.play();*//*

       *//* mPlayer = new MediaPlayer();
        *//**//*MediaPlayer mPlayer = new MediaPlayer();*//**//*
        try {
            if (Alconstants.ringtoneUri != null) {


                mPlayer.setDataSource(context, Alconstants.ringtoneUri);
                Log.e("RingtoneUri MessageActivity class",""+Alconstants.ringtoneUri);
                mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                //  mPlayer.setLooping(true);
                mPlayer.prepare();
                mPlayer.start();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }*//*

    }*/

  /*  public  void stopRingTone(){

        try {


            Log.e("stopRingtone()", "");



            try {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();



    }
}
