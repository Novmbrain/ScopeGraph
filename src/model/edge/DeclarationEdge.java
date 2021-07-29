package model.edge;

import model.scope.Name;
import model.scope.Node;
import model.scope.Scope;

import java.util.HashMap;

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

    public boolean isDeclarationEdge() {
        return true;
    }

    @Override
    public void selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap, Scope scope, Scope newScope) {

            Name newName  = newScope.constructDeclaration(end.getVariableName(), end.getVariableId());

            if (newNameMap.containsKey(newName.toString())) {
                return;
            }

            newNameMap.put(newName.toString(), newName);

            if(end.haveAssociationEdge()){
                if (newScopeMap.containsValue(end.getAssociationEdge().getEnd())) {
                    newName.constructAssociation(newScopeMap.get(((Scope)end.getAssociationEdge().getEnd()).getScopeId()));
                }{
                    newName.constructAssociation(((Scope)end.getAssociationEdge().getEnd()).selfCopy(newNameMap, newScopeMap));
                }
            }
    }



    @Override
    public String toString() {

        return start + "->" + end + "\n";

    }
}
