package Model.Edge;

import Model.Node;
import Model.Scope;

/***
 * A direct edge constraint specifies a direct l-labeled edge from scope s1 to scope s2
 * Graphically : O -> O
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class DirectEdge extends TaggedEdge{
    private Scope start;
    private Scope end;

    public DirectEdge(Scope start, Scope end) {
        this.start = start;
        this.end = end;
    }

    public DirectEdge(String tag, Scope start, Scope end) {
        super(tag);
        this.start = start;
        this.end = end;
    }


    public Scope getStart() {
        return start;
    }

    public Scope getEnd() {
        return end;
    }


}
