package Model.Edge;

import Model.Name;
import Model.Node;
import Model.Scope;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:20
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
}