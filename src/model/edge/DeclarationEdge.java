package model.edge;

import model.scope.Name;
import model.scope.Node;
import model.scope.Scope;

/**
 * A declaration constraint specifies that declaration belongs to scope
 * Graphically : O -> []
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 *
 */
public class DeclarationEdge extends Edge{

    private Scope start;
    private Name end;

    public DeclarationEdge(Scope start, Name end) {
        this.start = start;
        this.end = end;
    }

    public Name getEnd() {
        return end;
    }

    @Override
    public Node getStart() {
        return start;
    }

    @Override
    public String toString() {

        return start + "->" + end + "\n";

    }
}
