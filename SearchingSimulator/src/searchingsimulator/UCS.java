package searchingsimulator;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Dalam algoritma UCS, disini saya menggunakan priority queue untuk menyimpan cost dari setiap node 
 * Diurutkan berdasarkan prioritas cost dari setiap node 
 * @author Doni Andrian
 */

public class UCS {
    drawGraph dGraph;

    UCS(drawGraph dg) {
        dGraph = dg;
    }

    void doSearch() {
        dGraph.dg_nodes = new ArrayList<>();
        for (Node n : dGraph.graph.nodes) {
            dGraph.dg_nodes.add(n);
        }
    
        boolean found = false;
        
        
        dGraph.closedList = new ArrayList<>();
    
        Node in = dGraph.graph.nodes.get(dGraph.initNode);
        in.Pcost = 0; // inisialisasi cost 
        dGraph.openList.add(in);
        dGraph.dg_nodes.remove(in);
    
        while (dGraph.openList != null && !found) {
            PriorityQueue<Node> openList = new PriorityQueue<>((first, second) -> Double.compare(first.Pcost, second.Pcost));
            openList.addAll(dGraph.openList);
            Node cn = openList.poll(); 
            dGraph.closedList.add(cn);
            dGraph.openList.remove(cn);
            
            dGraph.delay(1000);
            if (cn.nodeNr != dGraph.goalNode) {
                for (Node child : dGraph.graph.nodes) {
                if (dGraph.dg_nodes.contains(child) &&
                        dGraph.graph.adjMatrix[cn.nodeNr][child.nodeNr] < 1000000.0) {
                    double cost = cn.Pcost + dGraph.graph.adjMatrix[cn.nodeNr][child.nodeNr];
                    if (cost < child.Pcost) {
                        child.Pcost = cost;
                        dGraph.openList.add(child);
                        dGraph.dg_nodes.remove(child);
                        
                    }
                }
            }

            }else{
                found = false;
            }

            dGraph.paint(dGraph.getGraphics());
            dGraph.delay(1000);
        }
    }
    
}
