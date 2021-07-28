package model.edge;

import model.scope.Node;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public abstract class TaggedEdge extends Edge{
    private String tag;

    public TaggedEdge() {
    }

    public TaggedEdge(String tag) {
        this.tag = tag;
    }

}
