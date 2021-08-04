package model.scope;

import model.edge.Edge;

import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public abstract class Node {

    public List<Edge> edges;

    public Node() {

    }

    public boolean haveAssociationEdge() {

        for (Edge edge : edges) {
            if (edge.isAssociationEdge()) {
                return true;
            }
        }
        return false;
    }

    public Edge getAssociationEdge(){
        for (Edge edge : edges) {
            if (edge.isAssociationEdge()) {
                return edge;
            }
        }

        return null;
    }

    public Node getParentScope(){
        for (Edge edge : edges) {
            if (edge.isDirectEdge()) {
                return edge.getEnd();
            }
        }

        return null;
    }

}
