package Model.Edge;

import Model.Node;
import Model.Scope;

/*** @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class DirectEdge extends TaggedEdge{

    private String tag = "P";
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

    public String getTag() {
        return tag;
    }

    public Scope getStart() {
        return start;
    }

    public Scope getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "DirectEdge{" +
                "tag='" + tag + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
