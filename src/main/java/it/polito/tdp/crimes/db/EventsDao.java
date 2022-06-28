package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.crimes.model.Distretto;
import it.polito.tdp.crimes.model.Event;



public class EventsDao {
	
	public List<Event> listAllEvents(Map<Long, Event> idMapEvents){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				if(!idMapEvents.containsKey(res.getLong("incident_id"))) {
					try {
						Event ev = new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic"));
						list.add(ev);
						idMapEvents.put(ev.getIncident_id(), ev);
				
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
					
				
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Integer> getAllYears() {
		final String sql = "SELECT DISTINCT YEAR(reported_date) as year "
				+ "FROM events";
		List<Integer> result = new LinkedList<Integer>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add(res.getInt("year"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> getAllMonths() {
		final String sql = "SELECT DISTINCT Month(reported_date) as year "
				+ "FROM events";
		List<Integer> result = new LinkedList<Integer>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add(res.getInt("year"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> getAllDays() {
		final String sql = "SELECT DISTINCT DAY(reported_date) as year "
				+ "FROM events";
		List<Integer> result = new LinkedList<Integer>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add(res.getInt("year"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Distretto> getVertici(int anno, Map<Integer, Distretto> idMapDistretti) {
		final String sql = "SELECT DISTINCT e.district_id AS id, AVG(e.geo_lat) AS lat, AVG  (e.geo_lon) AS lon "
				+ "FROM events e "
				+ "WHERE YEAR(e.reported_date) = ? "
				+ "GROUP BY id";
		List<Distretto> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				if(!idMapDistretti.containsKey(res.getInt("id"))) {
					LatLng pos = new LatLng(res.getDouble("lat"), res.getDouble("lon"));
					Distretto dis = new Distretto(res.getInt("id"), pos);
					result.add(dis);
					idMapDistretti.put(dis.getId(), dis);
				}else {
					Distretto dis = idMapDistretti.get(res.getInt("id"));
					result.add(dis);
				}
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}

}
