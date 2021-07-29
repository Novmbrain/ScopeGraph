package model.edge;

import model.scope.Name;
import model.scope.Scope;

import java.util.HashMap;

/**
 * A reference constraint specifies that reference belongs to scope
 * Graphically : O <- []
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class ReferenceEdge extends Edge{

    private Name start;
    private Scope end;

    public ReferenceEdge(Name start, Scope end) {
        this.start = start;
        this.end = end;
    }


    public boolean isReferenceEdge(){
        return true;
    }

    @Override
    public void selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap, Scope scope, Scope newScope) {

            Name newName = new Name(start.getVariableName(), start.getVariableId());

            if (newNameMap.containsKey(newName)) {
                return;
            }

            newNameMap.put(newName.toString(), newName);
            newName.constructReference(newScope);

    }

    public Scope getEnd() {
        return end;
    }

    public Name getStart() {
        return start;
    }

    @Override
    public String toString() {
        return start + "->" + end + "\n";
    }
}
