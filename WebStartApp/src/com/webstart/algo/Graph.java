package com.webstart.algo;

import java.util.ArrayList;
import java.util.List;

public class Graph {	
	List<Node> Nodes;
	List<Edge> Edges;
	
	public class Node {
	    public String name;
	    public List<Edge> connections;
	    Node(String n) {
	    	connections = new ArrayList<Edge>();
	    	this.name=n;
	    	Nodes.add(this);
	    }	    
	    public void addEdge(Edge e) {
	    	connections.add(e);
	    }
	    
	    public String getName() {
	    	return name;
	    }
	}
	
	public class Edge {
	    public Node start;
	    public Node end;
	    public int weight;
	    Edge(Node s, Node e, int w) {
	    	this.start = s;
	    	this.end = e;
	    	this.weight = w;
	    	this.start.addEdge(this);
	    	Edges.add(this);
	    }
	    public Node getStart() {
	    	return start;
	    }
	    public Node getEnd() {
	    	return end;
	    }
	    public int getWeight() {
	    	return weight;
	    }
	}
	
	public Graph(){
		Nodes = new ArrayList<Node>();
		Edges = new ArrayList<Edge>();
		Node n1 = new Node ("Bhagwan"); 
		Node n2 = new Node("Avadesh");
		Node n3 = new Node("Rajjan");
		Node n4 = new Node ("Bachhan"); 
		Node n5 = new Node("Ramu");
		Node n6 = new Node("Kamal");
		Node n7 = new Node ("Binnu"); 
		Node n8 = new Node("Munna");
		Node n9 = new Node("Bhunni");
		new Edge(n1, n2, 4);
		new Edge(n3, n4, 4);
		new Edge(n4, n5, 4);
		new Edge(n6, n7, 4);
		new Edge(n7, n8, 4);
		new Edge(n8, n9, 4);
		new Edge(n1, n3, 1);
		new Edge(n3, n6, 1);
		new Edge(n6, n1, 1);		
	}
	
	public List<Node> getNodesList() {
		return Nodes;
	}
	public List<Edge> getEdgesList() {
		return Edges;
	}	
	public int getNodesCount() {
		return Nodes.size();
	}
	public int getEdgesCount() {
		return Edges.size();
	}

	public String toJSNodeArray(){
	    StringBuffer sb = new StringBuffer();
	    sb.append("[");
	    for(int i=0; i<Nodes.size(); i++){
	        sb.append("\"").append(Nodes.get(i).getName()).append("\"");
	        if(i+1 < Nodes.size()){
	            sb.append(",");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
	public String toJSEdgeArray(){
	    StringBuffer sb = new StringBuffer();
	    sb.append("[");
	    for(int i=0; i<Edges.size(); i++){
	        sb.append("\"").append(Edges.get(i).getStart().getName()).append("\""); sb.append(",");
	        sb.append("\"").append(Edges.get(i).getEnd().getName()).append("\""); sb.append(",");
	        sb.append("\"").append(Integer.toString(Edges.get(i).getWeight())).append("\"");
	        if(i+1 < Edges.size()){
	            sb.append(",");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
}
