package model.edge;

import model.Name;
import model.Scope;

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

    public DeclarationEdge() {
    }

    public DeclarationEdge(Scope start, Name end) {
        this.start = start;
        this.end = end;
    }

    public Scope getStart() {
        return start;
    }

    public Name getEnd() {
        return end;
    }
}
