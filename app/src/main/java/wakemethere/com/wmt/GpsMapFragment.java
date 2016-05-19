package wakemethere.com.wmt;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import wakemethere.com.wmt.R;

public class GpsMapFragment extends Fragment implements OnClickListener {

    static int flag=0;
//    private GoogleMap mMap;
	private GPSTracker gps;
	public Context context;
	private Intent intent;
	private EditText editTextDistance;
	private Button buttonTrack;
	private Button buttonChooseRingtone;
	private ToggleButton buttonToggle;
	private static View rootView;

  	private GoogleMap googleMap;
	private SupportMapFragment supportMapFragment;
	private double latitude = 0.0;
	private double longitude = 0.0;
	private Marker marker;
	AndroidGlobalClass androidGlobalClass;
    MarkerOptions markerOption = null;
    ArrayList<AllDataModel> allDataModelArrayList;
    AllDataModel allDataModel =new AllDataModel();
    AlarmDBHelper db;
    private static final int RESULT_OK = -1;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}



	// place all UI component and click listener
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.e("onCreateView", "onCreateView");
		context = getActivity();



    //remove previous view
		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}

		try {
			rootView = inflater.inflate(R.layout.route_management, container,
					false);

		} catch (InflateException e) {
//map is already there, just return view as it is

		}

		buttonToggle = (ToggleButton) rootView.findViewById(R.id.buttonToggle);
		buttonToggle.setOnClickListener(this);

		editTextDistance = (EditText) rootView
				.findViewById(R.id.editTextDistance);

		buttonChooseRingtone = (Button) rootView
				.findViewById(R.id.buttonChooseRingtone);
		buttonChooseRingtone.setOnClickListener(this);


		

		return rootView;
	}



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("onActivityCreated", "onActivityCreated");
        try {

            allDataModelArrayList = new ArrayList<AllDataModel>();
            db=new AlarmDBHelper(context);

            allDataModelArrayList = db.getAllData();
            allDataModel.setTargetLatitude(allDataModelArrayList.get(0).getTargetLatitude());
            allDataModel.setTargetLongitude(allDataModelArrayList.get(0).getTargetLongitude());
            allDataModel.setDistance(allDataModelArrayList.get(0).getDistance());
            allDataModel.setRingtoneUri(allDataModelArrayList.get(0).getRingtoneUri());
            allDataModel.setToggleButtonStatus(allDataModelArrayList.get(0).getToggleButtonStatus());


            Log.e("Databasse table value of distance=", "" + allDataModel.getDistance());
            Log.e("Databasse table value of RingtoneUri=", "" + allDataModel.getRingtoneUri());
            Log.e("Databasse table value of TargetLatitude=", "" + allDataModel.getTargetLatitude());
            Log.e("Databasse table value of TargetLongitude=", "" + allDataModel.getTargetLongitude());
            Log.e("Databasse table value of ToggleButtonStatus=", "" + allDataModel.getToggleButtonStatus());

            if(allDataModelArrayList.size()>0){

                Alconstants.targetLatitude=allDataModelArrayList.get(0).getTargetLatitude();
                Alconstants.targetLongitude=allDataModelArrayList.get(0).getTargetLongitude();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         initializeMap();

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                try {

                    Log.d("setOnMapClickListener ","setOnMapClickListener");

                    latitude = latLng.latitude;
                    longitude = latLng.longitude;
                    if (latitude != 0.0 && longitude != 0.0) {

                        Toast.makeText(
                                context,
                                "Your Location is - \nLat: " + latitude
                                        + "\nLong: " + longitude,
                                Toast.LENGTH_LONG).show();


                        Alconstants.targetLatitude=latLng.latitude;
                        Alconstants.targetLongitude=latLng.longitude;


                        allDataModel.setTargetLatitude(latLng.latitude);
                        allDataModel.setTargetLongitude(latLng.longitude);
                        Log.e("onMapClick getTargetLatitude",""+   allDataModel.getTargetLatitude());
                        Log.e("onMapClick getTargetLongitude",""+ allDataModel.getTargetLongitude());

                        Log.e("onMapClick getTargetLatitude",""+   Alconstants.targetLatitude);
                        Log.e("onMapClick getTargetLongitude",""+ Alconstants.targetLongitude);



                        getCurrentPos();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });







    }

    @Override
	public void onResume() {

		super.onResume();

        if (googleMap == null) {
            googleMap = supportMapFragment.getMap();
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

        getCurrentPos();

		Log.e("onResume", "onResume");
        new AndroidGlobalClass(context);



	}


	public void initializeMap() {



       FragmentManager fm = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, supportMapFragment)
                    .commit();

        }
        if (googleMap == null) {
            googleMap = supportMapFragment.getMap();
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

	}




	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.buttonToggle:

			boolean on = ((ToggleButton) view).isChecked();


			if (on) {


                if(allDataModelArrayList.size()>0){
            //already has gps alarm need to update
            Alconstants.toggleButtonStatus="on";
            allDataModel.setToggleButtonStatus("on");

     // for new input to edit
                    if (editTextDistance.getEditableText()
                            .toString().trim().length()>0){

                        allDataModel.setDistance(Double.parseDouble(editTextDistance.getEditableText().toString().trim()));

                    }

// update data into table
            Log.e("update=:","update");
            Log.e("updatedistance=", "" + allDataModel.getDistance());
            Log.e("updategetRingtoneUri=", "" + allDataModel.getRingtoneUri());
            Log.e("updategetTargetLongitude=", "" + allDataModel.getTargetLongitude());
            Log.e("updategetTargetLatitude=", "" + allDataModel.getTargetLatitude());
            Log.e("updategetToggleButtonStatus=", "" + allDataModel.getToggleButtonStatus());
            db.updateRow(1, allDataModel.getTargetLatitude(), allDataModel.getTargetLongitude(),
            allDataModel.getRingtoneUri(), allDataModel.getDistance(), allDataModel.getToggleButtonStatus());


                    try {

                        getActivity().startService(new Intent(context, MyService.class));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }else {


                    //check all inputs;
                    if (Alconstants.ringtoneUri==null
                            ||  Alconstants.targetLatitude==0.0
                            ||Alconstants.targetLongitude==0.0) {


                        AlertMessage.showMessage(context, "", "Please input all fields");
// off the switch view

                        buttonToggle.setChecked(false);

                    } else {
// add data into table
                        Alconstants.toggleButtonStatus="on";
                        allDataModel.setToggleButtonStatus("on");

                        String distance;
                        if (editTextDistance.getEditableText()
                                .toString().trim().equalsIgnoreCase("")){
                            //set default value
                            Alconstants.distance=50.00;
                            allDataModel.setDistance(50.00);
                        }else{

                            allDataModel.setDistance(Double.parseDouble(editTextDistance.getEditableText().toString().trim()));
                            Alconstants.distance=Double.parseDouble(editTextDistance.getEditableText().toString().trim());

                        }

                        Log.e("distance=", "" + allDataModel.getDistance());
                        Log.e("distance=", "" + Alconstants.distance);
                        Log.e("Insert","Insert");
                        Log.e("distance=", "" + allDataModel.getDistance());
                        Log.e("getRingtoneUri=", "" + allDataModel.getRingtoneUri());
                        Log.e("getTargetLongitude=", "" + allDataModel.getTargetLongitude());
                        Log.e("getTargetLatitude=", "" + allDataModel.getTargetLatitude());
                        Log.e("getToggleButtonStatus=", "" + allDataModel.getToggleButtonStatus());
                        db.addAllData(allDataModel);


                        try {

                            getActivity().startService(new Intent(context, MyService.class));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                }
//end of first else of on



            } else {
                Alconstants.toggleButtonStatus="off";

				// stop service
				try {
					getActivity().stopService(new Intent(context, MyService.class));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			break;
		case R.id.buttonChooseRingtone:

            try {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, 1);
                Log.e("onStart()Execute","");

            }
            catch (Exception e)
            {e.printStackTrace();


            }

			break;
		default:
			Log.e("default", "default");
		}

	}





    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        try {

            Log.e("data",""+data.toString());


            if (resultCode == RESULT_OK) {

                Log.e("RESULT_OK",""+RESULT_OK);
                switch (requestCode)
                {
                    case 1: {
                        Log.e("requestCode",""+requestCode);

                        Uri uri=data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                        allDataModel.setRingtoneUri(uri.toString());
                        Log.e("Ringtone uri value",""+ allDataModel.getRingtoneUri());

                        /*Uri uri;*/
                        Alconstants.ringtoneUri=data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                        Log.e("Ringtone uri value",""+ Alconstants.ringtoneUri);
                        break;
                    }
                    default: {
                        break;
                    }
                }



            }

        }catch (Exception e){e.printStackTrace();}




    }

	private void getCurrentPos() {

		gps = new GPSTracker(context);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
			Log.e("longitude", "" + longitude);

			if (latitude == 0.0 || longitude == 0.0) {

		AlertMessage.showMessage(context, "","Your Device Could not get position");

			} else {

				setDataTOMap();
			}

			Toast.makeText(
					context,
					"Your Location is - \nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}

	}

	private void setDataTOMap() {


    try {

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(true);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

            try {

                if (googleMap != null) {
                    googleMap.clear();
                }

                if (marker != null) {
                    marker.remove();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e("latln",""+latitude+longitude);
			markerOption = new MarkerOptions().position(new LatLng(latitude,
					longitude));

			markerOption.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_RED));

			marker = googleMap.addMarker(markerOption);
			marker.showInfoWindow();

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(latitude, longitude)).zoom(16).build();
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

            if ((Alconstants.targetLatitude != 0.0) && (Alconstants.targetLongitude != 0.0)){


              //  Log.e("Status On is active ","");

                markerOption = new MarkerOptions().position(new LatLng(Alconstants.targetLatitude,
                        Alconstants.targetLongitude));
                markerOption.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                marker = googleMap.addMarker(markerOption);
                marker.showInfoWindow();

                CameraPosition cameraPos = new CameraPosition.Builder()
       .target(new LatLng(Alconstants.targetLatitude,Alconstants.targetLongitude)).zoom(16).build();
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPos));

            }



		} catch (Exception e) {

			e.printStackTrace();

		}

	}










}
