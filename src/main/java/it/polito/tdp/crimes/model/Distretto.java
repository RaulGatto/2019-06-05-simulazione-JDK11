package it.polito.tdp.crimes.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	
	private int id;
	private LatLng posizione;
	
	public Distretto(int id, LatLng posizione) {
		super();
		this.id = id;
		this.posizione = posizione;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LatLng getPosizione() {
		return posizione;
	}
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Distretto " + id + ", posizione=" + posizione;
	}
	
	

}
