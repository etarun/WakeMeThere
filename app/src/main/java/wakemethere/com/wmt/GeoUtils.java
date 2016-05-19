package wakemethere.com.wmt;

public class GeoUtils {

	private static int EARTH_RADIUS_KM = 6371;
	public static int MILLION = 1000000;

	/**
	 * * Computes the distance in kilometers between two points on Earth. * * @param
	 * lat1 Latitude of the first point * @param lon1 Longitude of the first
	 * point * @param lat2 Latitude of the second point * @param lon2 Longitude
	 * of the second point * @return Distance between the two points in
	 * kilometers.
	 */
	public static double distanceKm(final double lat1, final double lon1,
			final double lat2, final double lon2) {
		final double lat1Rad = Math.toRadians(lat1);
		final double lat2Rad = Math.toRadians(lat2);
		final double deltaLonRad = Math.toRadians(lon2 - lon1);
		return Math
				.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad)
						* Math.cos(lat2Rad) * Math.cos(deltaLonRad))
				* GeoUtils.EARTH_RADIUS_KM;
	}

	public static double distanceMile(double lat1, double lng1, double lat2,
			double lng2) {
		final double earthRadius = 3958.75;
		final double dLat = Math.toRadians(lat2 - lat1);
		final double dLng = Math.toRadians(lng2 - lng1);
		final double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		final double dist = earthRadius * c;

		return dist;
	}





}
