/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingsimulator;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class drawGraph extends JFrame {
    int width;
    int height;

    Graph graph;
    ArrayList<Node> dg_nodes;   // daftar semua node
    ArrayList<Edge> dg_edges;   // daftar semua sisi
    ArrayList<Node> openList;   // digunakan pada saat searching
    ArrayList<Node> closedList; // digunakan pada saat searching
    int initNode;               // simpul awal
    int goalNode;               // simpul tujuan  
    

    public drawGraph(File file) { //Constructor
	this.setTitle("Graph");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graph = new Graph();
	dg_nodes = new ArrayList<Node>();
	openList = new ArrayList<Node>();
	closedList = new ArrayList<Node>();
	dg_edges = new ArrayList<Edge>();
	width = 30;
	height = 30;
        setGraph(file);
    }

    void setGraph(File text){
        try {
            Scanner sc = new Scanner(text);
            int nC = sc.nextInt(); // banyaknya simpul
            int nE = sc.nextInt(); // banyaknya sisi
            initNode = sc.nextInt(); // simpul asal
            goalNode = sc.nextInt(); // simpul tujuan
            graph.adjMatrix = new double [nC][nC];
            for (int i = 0; i < nC; i++) {
                int nr = sc.nextInt();
                String s = sc.next();
                int x = sc.nextInt();
                int y = sc.nextInt();
                Node n = new Node(i,s,x,y);
                graph.nodes.add(n);
                dg_nodes.add(n);
            }
            
            for (int i=0; i<nC;i++){
                for (int j=0; j<nC;j++){
                    graph.adjMatrix[i][j]= 1000000.0; // tidak terhubung
                }
            }
            
            for (int i=0; i<nE; i++){
                int f = sc.nextInt();
                int s = sc.nextInt();
                double d = sc.nextDouble();
                Edge e = new Edge(graph.nodes.get(f),graph.nodes.get(s),d);
                graph.edges.add(e);
                dg_edges.add(e);
                graph.adjMatrix[s][f] = d;
                graph.adjMatrix[f][s] = d;
            }
        } catch (IOException ex) {
            System.err.println("File not found");
        }
    }
    
    public void paint(Graphics g) { // draw the nodes and edges
	FontMetrics f = g.getFontMetrics();
	int nodeHeight = Math.max(30, f.getHeight());

	g.setColor(Color.black);
	for (Edge e : dg_edges) {
	    g.drawLine(e.first.posX, e.first.posY,
		     e.second.posX, e.second.posY);
             //perbaikan GUI dengan memindahkan repaint ke kelas SimulatorFrame untuk menghindari pemanggilan repaint() secara berulang sehingga tidak terjadi blinking
           //repaint(); 
	}

	for (Node n : dg_nodes) {
	    int nodeWidth = Math.max(30, f.stringWidth(n.nodeLabel)+30/2);
	    g.setColor(Color.white);
	    g.fillOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    g.setColor(Color.black);
	    g.drawOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    
	    g.drawString(n.nodeLabel, n.posX-f.stringWidth(n.nodeLabel)/2,
			 n.posY+f.getHeight()/2);
             //perbaikan GUI dengan memindahkan repaint ke kelas SimulatorFrame untuk menghindari pemanggilan repaint() secara berulang sehingga tidak terjadi blinking
            //repaint();
	}

	for (Node n : openList) {
	    int nodeWidth = Math.max(30, f.stringWidth(n.nodeLabel)+30/2);
	    g.setColor(Color.blue);
	    g.fillOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    g.setColor(Color.black);
	    g.drawOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    
	    g.drawString(n.nodeLabel, n.posX-f.stringWidth(n.nodeLabel)/2,
			 n.posY+f.getHeight()/2);
             //perbaikan GUI dengan memindahkan repaint ke kelas SimulatorFrame untuk menghindari pemanggilan repaint() secara berulang sehingga tidak terjadi blinking
            //repaint();
	}

	for (Node n : closedList) {
	    int nodeWidth = Math.max(30, f.stringWidth(n.nodeLabel)+30/2);
	    g.setColor(Color.yellow);
	    g.fillOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    g.setColor(Color.black);
	    g.drawOval(n.posX-nodeWidth/2, n.posY-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    
	    g.drawString(n.nodeLabel, n.posX-f.stringWidth(n.nodeLabel)/2,
			 n.posY+f.getHeight()/2);
             //perbaikan GUI dengan memindahkan repaint ke kelas SimulatorFrame untuk menghindari pemanggilan repaint() secara berulang sehingga tidak terjadi blinking
            //repaint();
	}
    }    
    
    void delay(int d){
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
        }
    }
}