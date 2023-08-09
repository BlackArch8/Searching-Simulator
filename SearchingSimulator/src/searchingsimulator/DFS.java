package searchingsimulator;

import java.util.ArrayList;

/**
 *
 * Untuk DFS saya hanya mengubah sedikit dari BFS,
 * yaitu mengubah cara menambahkan node ke openList
 * Untuk BFS, node ditambahkan ke openList di akhir (mirip queue),
 * sedangkan untuk DFS saya menambahkan node ke openList di awal (mirip stack)
 * 
 * @author Doni Andrian
 */

public class DFS {
    // Graph graph;
    drawGraph dGraph;

    DFS(drawGraph dg) {
        dGraph = dg;
    }

    void doSearch() {
        // salin node di graph ke daftar simpul di dGraph
        dGraph.dg_nodes = new ArrayList<Node>();
        for (Node n : dGraph.graph.nodes) {
            dGraph.dg_nodes.add(n);
        }

        boolean found = false;
        dGraph.openList = new ArrayList<Node>();
        dGraph.closedList = new ArrayList<Node>();

        Node in = dGraph.graph.nodes.get(dGraph.initNode);
        dGraph.openList.add(in);
        dGraph.dg_nodes.remove(in);

        while ((dGraph.openList != null) && !found) {
            Node cn = dGraph.openList.get(0); // current node
            dGraph.closedList.add(cn);
            dGraph.openList.remove(cn);
            dGraph.delay(1000);

            if (cn.nodeNr != dGraph.goalNode) {
                for (Node child : dGraph.graph.nodes) {
                    if (dGraph.dg_nodes.contains(child) && // belum dikunjungi
                            dGraph.graph.adjMatrix[cn.nodeNr][child.nodeNr] < 1000000.0) {
                        dGraph.openList.add(0, child); // tambahkan di awal
                        dGraph.dg_nodes.remove(child);
                    }
                }

            } else {
                found = true;
            }
            dGraph.paint(dGraph.getGraphics());
            dGraph.delay(1000);
        }
    }

}
