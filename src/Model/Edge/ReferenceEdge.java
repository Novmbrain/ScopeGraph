package Model.Edge;

import Model.Name;
import Model.Node;
import Model.Scope;

import javax.swing.*;

/**
 * A reference constraint specifies that reference belongs to scope
 * Graphically : O <- []
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class ReferenceEdge extends Edge{

    private Name start;
    private Scope end;

    public ReferenceEdge() {
    }

    public ReferenceEdge(Name start, Scope end) {
        this.start = start;
        this.end = end;
    }

    public Name getStart() {
        return start;
    }

    public Scope getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "ReferenceEdge{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
