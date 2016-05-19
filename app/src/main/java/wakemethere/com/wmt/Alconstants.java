/*
 * Copyright (C) 2010 Mathieu Favez - http://mfavez.com
 *
 *
 * This file is part of FeedGoal.
 *
 * FeedGoal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeedGoal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FeedGoal.  If not, see <http://www.gnu.org/licenses/>.
 */

package wakemethere.com.wmt;

import android.net.Uri;

public final class Alconstants {

    public static final String LOG_TAG = "SharedPreferencesHelper";

	
	// App Preferences
    public static final String PREFS_FILE_NAME = "AppPreferences";

	private static final String userId = "";

//

    public static  Double targetLatitude =0.0;
    public static  Double targetLongitude =0.0;
	public static Uri ringtoneUri=null;
	public static Double distance=0.0;
    public static String toggleButtonStatus="off";

  /*  public static String targetGpsLatitude="0.0";
    public static String targetGpsLongitude="0.0";


    public static String getTargetGpsLatitude(final Context ctx) {
        return ctx.getSharedPreferences(
                Alconstants.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(Alconstants.targetGpsLatitude, "");
    }

    public static void setTargetGpsLatitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                Alconstants.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Alconstants.targetGpsLatitude, id);
        editor.commit();
    }


    public static String getTargetGpsLongitude(final Context ctx) {
        return ctx.getSharedPreferences(
                Alconstants.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(Alconstants.targetGpsLongitude, "");
    }

    public static void setTargetGpsLongitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                Alconstants.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Alconstants.targetGpsLongitude, id);
        editor.commit();
    }
*/

/*
	public static String getUserId(final Context ctx) {
		return ctx.getSharedPreferences(
				SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getString(SharedPreferencesHelper.userId, "");
	}
	
	public static void setUserId(final Context ctx, final String id) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(SharedPreferencesHelper.userId, id);
		editor.commit();
	}*/


   /* public static String getLatitude(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.latitude, "");
    }

    public static void setLatitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.latitude, id);
        editor.commit();
    }


    public static String getLongitude(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.longitude, "");
    }

    public static void setLongitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.longitude, id);
        editor.commit();
    }
    public static String getRingtoneUri(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.ringtoneUri, "");
    }


    public static void setRingtoneUri(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.ringtoneUri, id);
        editor.commit();
    }

    public static String getDistance(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.distance, "");
    }

    public static void setDistance(final Context ctx, final String id) {

        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.distance, id);
        editor.commit();


*//*        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.commit();*//*

    }


    public static String getToggleButtonStatus(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.toggleButtonStatus, "");
    }

    public static void setToggleButtonStatus(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.toggleButtonStatus, id);
        editor.commit();
    }

    public static String getTargetLatitude(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.targetLatitude, "");
    }

    public static void setTargetLatitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.targetLatitude, id);
        editor.commit();
    }


    public static String getTargetLongitude(final Context ctx) {
        return ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE)
                .getString(SharedPreferencesHelper.targetLongitude, "");
    }

    public static void setTargetLongitude(final Context ctx, final String id) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                SharedPreferencesHelper.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(SharedPreferencesHelper.targetLongitude, id);
        editor.commit();
    }
*/
}
