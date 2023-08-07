package searchingsimulator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class UCS {
    drawGraph dGraph;

    UCS(drawGraph dg) {
        dGraph = dg;
    }

    void doSearch() {
        // Copy nodes from graph to dg_nodes in dGraph
        dGraph.dg_nodes = new ArrayList<>(dGraph.graph.nodes);

        boolean found = false;
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.pathCost));
        HashSet<Node> openSet = new HashSet<>();
        dGraph.closedList = new ArrayList<>();

        Node in = dGraph.graph.nodes.get(dGraph.initNode);
        in.pathCost = 0;
        openList.add(in);
        openSet.add(in);
        dGraph.dg_nodes.remove(in);

        while (!openList.isEmpty() && !found) {
            Node cn = openList.poll(); // current node
            openSet.remove(cn);
            dGraph.closedList.add(cn);
            dGraph.delay(1000);

            if (cn.nodeNr != dGraph.goalNode) {
                for (Node child : dGraph.graph.nodes) {
                    double newPathCost = cn.pathCost + dGraph.graph.adjMatrix[cn.nodeNr][child.nodeNr];
                    if (dGraph.dg_nodes.contains(child) && // belum dikunjungi
                            dGraph.graph.adjMatrix[cn.nodeNr][child.nodeNr] < 1000000.0) {
                        child.pathCost = newPathCost;
                        openList.add(child);
                        openSet.add(child);
                        dGraph.dg_nodes.remove(child);
                    } else if (openSet.contains(child) && newPathCost < child.pathCost) {
                        // If the node is in openSet, update its pathCost and re-add it to the PriorityQueue
                        openList.remove(child);
                        child.pathCost = newPathCost;
                        openList.add(child);
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
