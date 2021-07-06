package Model.Edge;

import Model.Name;
import Model.Node;
import Model.Scope;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:21
 */
public class NominalEdge extends TaggedEdge{
    private String tag;
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
}
