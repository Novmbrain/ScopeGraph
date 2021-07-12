package model.edge;

import model.Name;
import model.Scope;

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

    public AssociationEdge() {
    }

    public AssociationEdge(Name start, Scope end) {
        this.start = start;
        this.end = end;
    }

    public Name getStart() {
        return start;
    }

    public Scope getEnd() {
        return end;
    }
}
