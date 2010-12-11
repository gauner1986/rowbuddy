package de.rowbuddy.util;

/**
 * Taken from:
 * http://stackoverflow.com/questions/3715521/how-can-i-calculate-the-distance-
 * between-two-gps-points-in-java
 * 
 * @author unknown
 */

public class GpsUtility {

	public static double distanceKm(double startLat, double startLong,
			double endLat, double endLong) {

		double d2r = (Math.PI / 180);

		double dlong = (endLong - startLong) * d2r;
		double dlat = (endLat - startLat) * d2r;
		double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(startLat * d2r)
				* Math.cos(endLat * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = 6367 * c;

		return distance;
	}

}
