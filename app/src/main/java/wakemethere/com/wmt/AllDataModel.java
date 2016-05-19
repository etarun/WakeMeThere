package wakemethere.com.wmt;

/**
 * Created by arif on 15/04/2015.
 */
public class AllDataModel {
    public  Double targetLatitude =0.0;
    public   Double targetLongitude =0.0;
    public  String ringtoneUri=null;
    public  Double distance=0.0;
    public  String toggleButtonStatus="off";

    public Double getTargetLatitude() {
        return targetLatitude;
    }

    public void setTargetLatitude(Double targetLatitude) {
        this.targetLatitude = targetLatitude;
    }

    public String getToggleButtonStatus() {
        return toggleButtonStatus;
    }

    public void setToggleButtonStatus(String toggleButtonStatus) {
        this.toggleButtonStatus = toggleButtonStatus;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getRingtoneUri() {
        return ringtoneUri;
    }

    public void setRingtoneUri(String ringtoneUri) {
        this.ringtoneUri = ringtoneUri;
    }

    public Double getTargetLongitude() {
        return targetLongitude;
    }

    public void setTargetLongitude(Double targetLongitude) {
        this.targetLongitude = targetLongitude;
    }

    public  int id = 1;

    public AllDataModel(){}
    public AllDataModel(Double latitude, Double longitude, String ringtone, Double distance, String toggleStatus){
        this.targetLatitude = latitude;
        this.targetLongitude = longitude;
        this.ringtoneUri = ringtone;
        this.distance = distance;
        this.toggleButtonStatus = toggleStatus;

    }




}
