package Model.Edge;

import Model.Name;
import Model.Node;
import Model.Scope;

/**
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
}
