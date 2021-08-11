package model.edge;

import model.scope.Name;
import model.scope.Node;
import model.scope.Scope;

import java.util.HashMap;

/**
 * A nominal edge constraint specifies a nominal l-labeled edge from scope s to reference.
 * Such an edge makes visible in s all declarations that are visible in the associated scope of the declaration.
 * Graphically : O -> []
 *
 * @author Wenjie FU
 * @create 2021-06-30 14:21
 */
public class NominalEdge extends TaggedEdge {
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

    public Name getEnd() {
        return end;
    }

    public boolean isNominalEdge() {
        return true;
    }

    @Override
    public void selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap, Scope scope, Scope newScope) {


        Name newName = newScope.constructNominalEdge(end.getVariableName(), end.getVariableId() + COPY_ADD_NUMBER);
        if (newNameMap.containsValue(newName)) {
            return;
        }

        newNameMap.put(newName.toString(), newName);
    }

    @Override
    public Node getStart() {
        return start;
    }

    @Override
    public String toString() {
        return start + "-> " + end + " " + "[arrowhead=empty, label=I, color=red]" + "\n";
    }
}
