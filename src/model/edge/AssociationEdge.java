package model.edge;

import model.scope.Name;
import model.scope.Node;
import model.scope.Scope;

import java.util.HashMap;

/**
 * An association constraint specifies s as the associated scope of declaration. Associated scopes
 * can be used to connect the declaration of a collection of names to the scope declaring those names(e.g. the body of a module)
 * Graphically : [] -> O
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class AssociationEdge extends Edge{

    private Name start;
    private Scope end;

    public AssociationEdge(Name start, Scope end) {
        this.start = start;
        this.end = end;
    }


    public Scope getEnd() {
        return end;
    }

    @Override
    public Node getStart() {
        return start;
    }

    public boolean isAssociationEdge(){
        return true;
    }

    @Override
    public void selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap, Scope scope, Scope newScope) {

    }

    @Override
    public String toString() {
        return start + "->" + end + " " + "[arrowhead=empty]" + "\n";
    }
}
