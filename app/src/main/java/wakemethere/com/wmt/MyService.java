package wakemethere.com.wmt;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import wakemethere.com.wmt.R;

public class MyService extends Service {
	Activity act;
	// private GPSTracker gps;
	public Context context=this;
	public Context contextRing;
	private GPSTracker gps;
	public Handler handler;
	private double latitude = 0.0;
	private double longitude = 0.0;
	public PowerManager.WakeLock pwakeLock;
	PowerManager pm;
    AndroidGlobalClass androidGlobalClass;

    ArrayList<AllDataModel> allDataModelArrayList;
    AlarmDBHelper db;

	@Override
	public void onCreate() {
		super.onCreate();
		handler = new Handler();
		Log.d("onCreate()", "Service Created");
        androidGlobalClass = new AndroidGlobalClass(getApplicationContext());
        allDataModelArrayList = new ArrayList<AllDataModel>();
        db=new AlarmDBHelper(context);
	}

	
	
	// TimerTask notifyTask;
	Timer timer = new Timer();

	@Override
	public void onStart(final Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d("onStart()", "Service Started");


        GpsMapFragment.flag=1;



		// Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					public void run() {


						Log.d("MyService", "Service is Running");

						try {

							if (pwakeLock.isHeld()) {
                                Log.e("pwakeLock.release() is called","");
								pwakeLock.release();

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						 pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
						pwakeLock = pm.newWakeLock(	PowerManager.SCREEN_DIM_WAKE_LOCK,"My Tag");
						pwakeLock.acquire();


                        try {
                            allDataModelArrayList =db.getAllData();
                            Log.e("ArrayList Size", "" + allDataModelArrayList.size());
                            Log.e("Target latitude ", "" + allDataModelArrayList.get(0).getTargetLatitude());
                            Log.e("Target longitude ", "" + allDataModelArrayList.get(0).getTargetLongitude());
                            // Log.e("TBS ", "" + allDataModelArrayList.get(0).getToggleButtonStatus());
                            Log.e("Distance ", "" + allDataModelArrayList.get(0).getDistance());
                            Log.e("RingtoneUri  ", "" + allDataModelArrayList.get(0).getRingtoneUri());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {

							gps = new GPSTracker(context);

							// check if GPS enabled
							if (gps.canGetLocation()) {

								latitude = gps.getLatitude();
								longitude = gps.getLongitude();

								if (latitude != 0.0 && longitude != 0.0) {
									


             Log.e("4 points=", ""+ allDataModelArrayList.get(0).getTargetLatitude()+ allDataModelArrayList.get(0).getTargetLongitude()+latitude+longitude);

					/*double difference= GeoUtils.distanceKm(Alconstants.targetLatitude,
                            Alconstants.targetLongitude,latitude,longitude);*/

                 double difference= GeoUtils.distanceKm(allDataModelArrayList.get(0).getTargetLatitude(),
                                            allDataModelArrayList.get(0).getTargetLongitude(),latitude,longitude);

				difference=difference*1000.00;
				Log.e("difference=", ""+difference+"meter");

                                    //Log.e("input radious =", ""+Alconstants.distance+"meter");
                                    Log.e("input radious =", ""+ allDataModelArrayList.get(0).getDistance()+"meter");
									//if (difference<=Alconstants.distance)


                                        if (difference<= allDataModelArrayList.get(0).getDistance())
                                    {
										
										//use callback interface method to make ringtone 
										
										try {

						pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
						pwakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"My Tag");
						pwakeLock.acquire();

										} catch (Exception e) {
											e.printStackTrace();
										}	
										
										// Open a new activity called GCMMessageView
										Intent intent = new Intent(context, MessageActivity.class);
										// Pass data to the new activity
										
										intent.putExtra("message", "yes");									
										
										// Starts the activity on notification click
										PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
												PendingIntent.FLAG_UPDATE_CURRENT);
										// Create the notification with a notification builder
										Notification notification = new Notification.Builder(context)
												.setSmallIcon(R.drawable.ic_launcher)
												.setWhen(System.currentTimeMillis())
												.setContentTitle("Destination Arrived")           /*Place has found*/
												.setContentText("Tracking has completed ").setContentIntent(pIntent)
												.getNotification();
										// Remove the notification on click
										notification.flags |= Notification.FLAG_AUTO_CANCEL;

										NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
										manager.notify(R.string.app_name, notification);


										//  create a handler to post messages to the main thread
									    Handler mHandler = new Handler(getMainLooper());
									    mHandler.post(new Runnable() {
									        @Override
									        public void run() {
									        	


									    		try {

                                                    Log.e("startRingtone",""+allDataModelArrayList.get(0).getRingtoneUri().trim());
									    			androidGlobalClass.startRingtone(allDataModelArrayList.get(0).getRingtoneUri().trim());


									    		} catch (Exception e) {
									    			
									    			e.printStackTrace();
									    		}
									    		
									        }
									    });
											
										
										
										//stop the service
										stopSelf();
									
										
									}
	 				
	 
								
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						

					}
				});
			}
		};

		timer.schedule(timerTask, 0, 60000 * 1); // execute in every 1m

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
		Log.d("onDestroy()", "Service Destroyed");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
