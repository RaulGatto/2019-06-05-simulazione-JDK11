package it.polito.tdp.crimes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {

	private Graph<Distretto, DefaultWeightedEdge> graph;
	private Map<Integer, Distretto> idMapDistretti;
	private List<Distretto> vertici;
	private EventsDao dao;
	
	public Model() {
		this.dao = new EventsDao();
		this.idMapDistretti = new HashMap<>();

	}
	
	public String creaGrafo(int anno) {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici = this.dao.getVertici(anno, idMapDistretti);
		Graphs.addAllVertices(this.graph, vertici);
		
		for(Distretto d1 : this.graph.vertexSet()) {
			for(Distretto d2 : this.graph.vertexSet()) {
				if(!d1.equals(d2)) {
					double distance = LatLngTool.distance(d1.getPosizione(), d2.getPosizione(), LengthUnit.KILOMETER);
					Graphs.addEdgeWithVertices(this.graph, d1, d2, distance);
				}
			}
		}
		
		return String.format("E stato creato un grafo con %d vertici e %d archi", this.graph.vertexSet().size(), this.graph.edgeSet().size());
	}

	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return this.dao.getAllYears();
	}

	public List<Integer> getAllMonths() {
		// TODO Auto-generated method stub
		return this.dao.getAllMonths();
	}

	public List<Integer> getAllDays() {
		// TODO Auto-generated method stub
		return this.dao.getAllDays();
	}

	public List<Distretto> getVertici() {
		if(vertici.size() != 0)
			return vertici;
		else
			return null;
	}
	
	
	
}
