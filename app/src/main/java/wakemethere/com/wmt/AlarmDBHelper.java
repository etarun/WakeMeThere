package wakemethere.com.wmt;
/**
 * Created by Tarun on 2/27/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import wakemethere.com.wmt.AlarmContract.Alarm;

public class AlarmDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "alarmclock.db";
	
	private static final String SQL_CREATE_ALARM = "CREATE TABLE " + Alarm.TABLE_NAME + " (" +
			Alarm._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			Alarm.COLUMN_NAME_ALARM_NAME + " TEXT," +
			Alarm.COLUMN_NAME_ALARM_TIME_HOUR + " INTEGER," +
			Alarm.COLUMN_NAME_ALARM_TIME_MINUTE + " INTEGER," +
			Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS + " TEXT," +
			Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY + " BOOLEAN," +
			Alarm.COLUMN_NAME_ALARM_TONE + " TEXT," +
			Alarm.COLUMN_NAME_ALARM_ENABLED + " BOOLEAN" +
	    " )";

    private static  String CREATE_TABLE_GPS_ALARM = "CREATE TABLE " + Alarm.TABLE_NAME_GPS_ALARM + " (" +
            Alarm.COLUMN_NAME_ID + " INTEGER PRIMARY KEY ," +
            Alarm.COLUMN_NAME_TARGET_LATITUDE + " REAL," +
            Alarm.COLUMN_NAME_TARGET_LONGITUDE + " REAL," +
            Alarm.COLUMN_NAME_RINGTONE_URI + " TEXT," +
            Alarm.COLUMN_NAME_DISTANCE + " REAL," +
            Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS + " TEXT" +

            " )";
	
	private static final String SQL_DELETE_ALCONSTANT =
		    "DROP TABLE IF EXISTS " + Alarm.TABLE_NAME_GPS_ALARM;

    private static final String SQL_DELETE_ALLCONSTANT =
            "DELETE * FROM " + Alarm.TABLE_NAME_GPS_ALARM;

    private static final String SQL_DELETE_ALARM =
            "DROP TABLE IF EXISTS " + Alarm.TABLE_NAME;


    
	public AlarmDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        try {
            Log.e("mydb", "mydb");
            db.execSQL(SQL_CREATE_ALARM);
            db.execSQL(CREATE_TABLE_GPS_ALARM);
        }catch(Exception e){
            Log.e("mydbError", "mydbError");
            e.printStackTrace();
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ALARM);
        db.execSQL(SQL_DELETE_ALCONSTANT);
        onCreate(db);
	}

    public  void addAllData(AllDataModel allDataModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Alarm.COLUMN_NAME_ID, allDataModel.id);
        values.put(Alarm.COLUMN_NAME_TARGET_LATITUDE, allDataModel.getTargetLatitude());
        values.put(Alarm.COLUMN_NAME_TARGET_LONGITUDE, allDataModel.getTargetLongitude());
        values.put(Alarm.COLUMN_NAME_RINGTONE_URI, allDataModel.getRingtoneUri());
        values.put(Alarm.COLUMN_NAME_DISTANCE, allDataModel.getDistance());
        //values.put(Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS, allDataModel.getToggleButtonStatus());
        values.put(Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS, allDataModel.getToggleButtonStatus());

        try {
            Log.d("dbInsert", "yes");
            db.insert(Alarm.TABLE_NAME_GPS_ALARM, null, values);
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_ID));
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_TARGET_LATITUDE));
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_TARGET_LONGITUDE));
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_DISTANCE));
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_RINGTONE_URI));
            Log.d("dbInsert=:", " "+values.get(Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS));
        } catch (Exception e) {
            Log.d("dbInsertError", "yes");
                e.printStackTrace();
        }

        db.close();


    }

   /* public  void deleteall(){
        Log.d("dbDelete=:", "yes");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ALLCONSTANT);
    }*/
    public  ArrayList<AllDataModel> getAllData(){

        ArrayList<AllDataModel> allDataModelArrayList = new ArrayList<AllDataModel>();
        String selectQuery = "SELECT  * FROM " + Alarm.TABLE_NAME_GPS_ALARM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("i", "timedb");
        if (cursor.moveToFirst()){

            do{
                AllDataModel allDataModel = new AllDataModel();

                allDataModel.setTargetLatitude(cursor.getDouble(1));
                allDataModel.setTargetLongitude(cursor.getDouble(2));
                allDataModel.setRingtoneUri(cursor.getString(3));
                allDataModel.setDistance(cursor.getDouble(4));
                allDataModel.setToggleButtonStatus(cursor.getString(5));

                try {
                    allDataModelArrayList.add(allDataModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                allDataModel=null;

            }while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return allDataModelArrayList;
    }

    public  void  updateRow(int id,Double targetLatitude,Double targetLongitude,String ringtoneUri,Double distance,String toggleButtonStatus){
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Alarm.COLUMN_NAME_ID, id);
        values.put(Alarm.COLUMN_NAME_TARGET_LATITUDE, targetLatitude);
        values.put(Alarm.COLUMN_NAME_TARGET_LONGITUDE, targetLongitude);
        values.put(Alarm.COLUMN_NAME_RINGTONE_URI, ringtoneUri);
        values.put(Alarm.COLUMN_NAME_DISTANCE, distance);
        values.put(Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS, toggleButtonStatus);



        try {
            /*Log.e("update=:"," "+targetLatitude);
            Log.e("update=:"," "+targetLongitude);
            Log.e("update=:"," "+ringtoneUri.toString());
            Log.e("update=:"," "+distance);
            Log.e("update=:"," "+toggleButtonStatus.toString());
            Log.e("Target=:"," "+values.get(Alarm.COLUMN_NAME_TARGET_LATITUDE));*/
            int x=db.update(Alarm.TABLE_NAME_GPS_ALARM, values, Alarm.COLUMN_NAME_ID +"="+id, null);
            Log.e("pname update stat ",""+x);
            Log.d("updateID=:", " "+values.get(Alarm.COLUMN_NAME_ID));
            Log.d("updateLAT=:", " "+values.get(Alarm.COLUMN_NAME_TARGET_LATITUDE));
            Log.d("updateLONG=:", " "+values.get(Alarm.COLUMN_NAME_TARGET_LONGITUDE));
            Log.d("updateDIS=:", " "+values.get(Alarm.COLUMN_NAME_DISTANCE));
            Log.d("updateURI=:", " "+values.get(Alarm.COLUMN_NAME_RINGTONE_URI));
            Log.d("updateTBS=:", " "+values.get(Alarm.COLUMN_NAME_TOGGLE_BUTTON_STATUS));
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private AlarmModel populateModel(Cursor c) {
		AlarmModel model = new AlarmModel();
		model.id = c.getLong(c.getColumnIndex(Alarm._ID));
		model.name = c.getString(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_NAME));
		model.timeHour = c.getInt(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_TIME_HOUR));
		model.timeMinute = c.getInt(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_TIME_MINUTE));
		model.repeatWeekly = c.getInt(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY)) == 0 ? false : true;
		model.alarmTone = c.getString(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_TONE)) != "" ? Uri.parse(c.getString(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_TONE))) : null;
		model.isEnabled = c.getInt(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_ENABLED)) == 0 ? false : true;
		
		String[] repeatingDays = c.getString(c.getColumnIndex(Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS)).split(",");
		for (int i = 0; i < repeatingDays.length; ++i) {
			model.setRepeatingDay(i, repeatingDays[i].equals("false") ? false : true);
		}
		
		return model;
	}
	
	private ContentValues populateContent(AlarmModel model) {
		ContentValues values = new ContentValues();
        values.put(Alarm.COLUMN_NAME_ALARM_NAME, model.name);
        values.put(Alarm.COLUMN_NAME_ALARM_TIME_HOUR, model.timeHour);
        values.put(Alarm.COLUMN_NAME_ALARM_TIME_MINUTE, model.timeMinute);
        values.put(Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY, model.repeatWeekly);
        values.put(Alarm.COLUMN_NAME_ALARM_TONE, model.alarmTone != null ? model.alarmTone.toString() : "");
        values.put(Alarm.COLUMN_NAME_ALARM_ENABLED, model.isEnabled);
        
        String repeatingDays = "";
        for (int i = 0; i < 7; ++i) {
        	repeatingDays += model.getRepeatingDay(i) + ",";
        }
        values.put(Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS, repeatingDays);
        
        return values;
	}
	
	public long createAlarm(AlarmModel model) {
		ContentValues values = populateContent(model);
        return getWritableDatabase().insert(Alarm.TABLE_NAME, null, values);
	}
	
	public long updateAlarm(AlarmModel model) {
		ContentValues values = populateContent(model);
        return getWritableDatabase().update(Alarm.TABLE_NAME, values, Alarm._ID + " = ?", new String[] { String.valueOf(model.id) });
	}
	
	public AlarmModel getAlarm(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
        String select = "SELECT * FROM " + Alarm.TABLE_NAME + " WHERE " + Alarm._ID + " = " + id;
		
		Cursor c = db.rawQuery(select, null);
		
		if (c.moveToNext()) {
            db.close();
			return populateModel(c);
		}
		
		return null;
	}
	
	public List<AlarmModel> getAlarms() {
		SQLiteDatabase db = this.getReadableDatabase();
		
        String select = "SELECT * FROM " + Alarm.TABLE_NAME;
		
		Cursor c = db.rawQuery(select, null);
		
		List<AlarmModel> alarmList = new ArrayList<AlarmModel>();
		
		while (c.moveToNext()) {
			alarmList.add(populateModel(c));
		}
		
		if (!alarmList.isEmpty()) {
            db.close();
			return alarmList;
		}
		
		return null;
	}
	
	public int deleteAlarm(long id) {
		return getWritableDatabase().delete(Alarm.TABLE_NAME, Alarm._ID + " = ?", new String[] { String.valueOf(id) });
	}
}
