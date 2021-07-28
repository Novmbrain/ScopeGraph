package model.edge;

import model.scope.Node;

/**
 * 。。。。。。。。
 *
 *
 *
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public abstract class Edge {

    public Edge() {
    }


    public abstract Node getEnd();

    public abstract Node getStart();

    public boolean isAssociationEdge(){
        return false;
    }

    public boolean isReferenceEdge(){
        return false;
    }

    public boolean isDirectEdge() {
        return false;
    }

    public AssociationEdge getReferenceEdge() {
        return null;
    }


}
