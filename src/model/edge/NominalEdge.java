package model.edge;

import model.Name;
import model.Scope;

/**
 * A nominal edge constraint specifies a nominal l-labeled edge from scope s to reference.
 * Such an edge makes visible in s all declarations that are visible in the associated scope of the declaration.
 * Graphically : O -> []
 * @author Wenjie FU
 * @create 2021-06-30 14:21
 */
public class NominalEdge extends TaggedEdge{
    private Scope start;
    private Name end;

    public NominalEdge(Scope start, Name end) {
        this.start = start;
        this.end = end;
    }

    public NominalEdge(String tag, Scope start, Name end) {
        super(tag);
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
