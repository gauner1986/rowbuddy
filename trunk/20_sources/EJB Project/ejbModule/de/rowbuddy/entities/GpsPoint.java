package de.rowbuddy.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.rowbuddy.util.GpsUtility;

/**
 * Entity implementation class for Entity: GpsPoint
 * 
 */
@Entity
public class GpsPoint implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double longitude = 0;
	private double latitude = 0;
	private static final long serialVersionUID = 1L;

	public GpsPoint() {
		super();
	}

	public GpsPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double distanceKmTo(GpsPoint otherPoint) {
		return GpsUtility.distanceKm(this.latitude, this.longitude,
				otherPoint.latitude, otherPoint.longitude);
	}

}
