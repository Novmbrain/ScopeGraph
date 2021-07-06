package Model;

import java.util.ArrayList;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public class ScopeGraph {

    private ArrayList<Node> nodeList;

    public ScopeGraph() {
    }

    public ScopeGraph(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
