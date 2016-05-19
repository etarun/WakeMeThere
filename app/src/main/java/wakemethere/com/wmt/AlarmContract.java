package wakemethere.com.wmt;
/**
 * Created by Tarun on 2/27/2015.
 */
import android.provider.BaseColumns;

public final class AlarmContract {
	
	public AlarmContract() {}
	
	public static abstract class Alarm implements BaseColumns {
		public static final String TABLE_NAME = "alarm";
		public static final String COLUMN_NAME_ALARM_NAME = "name";
		public static final String COLUMN_NAME_ALARM_TIME_HOUR = "hour";
		public static final String COLUMN_NAME_ALARM_TIME_MINUTE = "minute";
		public static final String COLUMN_NAME_ALARM_REPEAT_DAYS = "days";
		public static final String COLUMN_NAME_ALARM_REPEAT_WEEKLY = "weekly";
		public static final String COLUMN_NAME_ALARM_TONE = "tone";
		public static final String COLUMN_NAME_ALARM_ENABLED = "enabled";


        public static final String TABLE_NAME_GPS_ALARM = "gpsAlarm";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TARGET_LATITUDE = "latitude";
        public static final String COLUMN_NAME_TARGET_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_RINGTONE_URI = "ringtoneuri";
        public static final String COLUMN_NAME_DISTANCE= "distance";
        public static final String COLUMN_NAME_TOGGLE_BUTTON_STATUS = "togglebuttonstatus";
	}
	
}
