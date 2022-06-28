package it.polito.tdp.crimes.model;

public class Vicino {

	private Distretto id;
	private double distanza;
	public Vicino(Distretto id, double distanza) {
		super();
		this.id = id;
		this.distanza = distanza;
	}
	public Distretto getId() {
		return id;
	}
	public void setId(Distretto id) {
		this.id = id;
	}
	public double getDistanza() {
		return distanza;
	}
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	@Override
	public String toString() {
		return "Vicino " + id + ", distanza " + distanza +"\n";
	}
	
	
	
}
