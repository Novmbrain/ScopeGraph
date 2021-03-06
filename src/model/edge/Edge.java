package model.edge;

import model.scope.Name;
import model.scope.Node;
import model.scope.Scope;

import java.util.HashMap;

/**
 *
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public abstract class Edge {

    //Use copyAddNumber to add to the id of the replicated Node to distinguish the old Node from the new Node
    public static final int COPY_ADD_NUMBER = 100;

    public Edge() {
    }



    public abstract Node getEnd();

    public abstract Node getStart();

    public boolean isAssociationEdge() {
        return false;
    }

    public boolean isReferenceEdge() {
        return false;
    }

    public boolean isDirectEdge() {
        return false;
    }

    public boolean isDeclarationEdge() {
        return false;
    }

    public boolean isNominalEdge() {
        return false;
    }

    public AssociationEdge getReferenceEdge() {
        return null;
    }




    public abstract void selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap, Scope scope, Scope newScope);
}
