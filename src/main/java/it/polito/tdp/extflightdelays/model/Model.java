package it.polito.tdp.extflightdelays.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport,DefaultWeightedEdge> grafo ;
	private ExtFlightDelaysDAO dao ;
	private ArrayList<Airline> airline;
	private ArrayList<Airport> airport;
	private ArrayList<Flight> flight;
	private Map<Integer,Airport> aereoporti;
	private Map<Integer,Airport> airports;
	
	public Model() {
		
		dao = new ExtFlightDelaysDAO();
	}
	/*
	public void creaGrafo() {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		airline = new ArrayList<>(dao.loadAllAirlines());
		airport = new ArrayList<>(dao.loadAllAirports());
		aereoporti = new HashMap<>();
		
		for(Airport a : airport) {
			aereoporti.put(a.getId(),a);
		}
		
		
		flight = new ArrayList<>(dao.loadAllFlights());
		
		Graphs.addAllVertices(grafo, airport);
		for(Flight f : flight) {
			Graphs.addEdge(grafo, aereoporti.get(f.getOriginAirportId()), aereoporti.get(f.getDestinationAirportId()),f.getDistance());
		}
	}
	*/
	public String getNVertici() {
		return String.valueOf(this.grafo.vertexSet().size());
	}
	public String getNArchi() {
		return String.valueOf(this.grafo.edgeSet().size());
	}
	public String creaGrafoN(int N) {
		
		String tot="";
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		flight = new ArrayList<>(dao.loadAllFlights(N));
	//	flight = new ArrayList<>(dao.loadAllFlights());
		aereoporti = new HashMap<>();
		airports = new HashMap<>();
		airport = new ArrayList<>(dao.loadAllAirports());
		
		for(Airport a : airport) {
			aereoporti.put(a.getId(),a);
		}
		
		for(Flight f : flight) {
			airports.put(f.getOriginAirportId(), aereoporti.get(f.getOriginAirportId()));
			airports.put(f.getDestinationAirportId(), aereoporti.get(f.getDestinationAirportId()));
			}
	
			Graphs.addAllVertices(grafo, airports.values());
			for(Flight ff : flight) {
				Graphs.addEdge(grafo, airports.get(ff.getOriginAirportId()), airports.get(ff.getDestinationAirportId()),ff.getDistance());
				tot+= String.format("%-50s %-50s %-25d \n",airports.get(ff.getOriginAirportId()),
						airports.get(ff.getDestinationAirportId()),ff.getDistance());
			}
/*
			
			for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
				tot+= d.toString() ++"\n";
			}*/
		//return String.valueOf(grafo);
		return tot;
	}
//}tot+= String.format("%-16s %-16s %-10d ,airports.get(ff.getOriginAirportId()) +"-->""
		//+ "+airports.get(ff.getDestinationAirportId())+"  Weight : "+ff.getDistance()+"\n";
}
