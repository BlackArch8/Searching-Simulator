/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingsimulator;

import java.util.ArrayList;

/**
 *
 * @author CEN
 */
public class Graph {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    double [][] adjMatrix; 
    
    Graph(){
	nodes = new ArrayList<Node>();
	edges = new ArrayList<Edge>();
        adjMatrix = null; 
    }

    Graph(int nn){
	nodes = new ArrayList<Node>();
	edges = new ArrayList<Edge>();
        adjMatrix = new double [nn][nn];
    }
}
