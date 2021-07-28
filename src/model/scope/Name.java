package model.scope;

import model.edge.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public class Name extends Node{

    private String variableName;
    private int variableId;

    private List<Edge> edges;

    public Name(String variableName, int variableId) {
        this.variableName = variableName;
        this.variableId = variableId;
        edges = new LinkedList<>();
    }

    protected void constructAssociation(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        AssociationEdge associationEdge = new AssociationEdge(this, scope);
        this.addEdge(associationEdge);
        scope.addEdge(associationEdge);
    }

    protected void constructReference(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        ReferenceEdge referenceEdge = new ReferenceEdge(this, scope);
        this.addEdge(referenceEdge);
        scope.addEdge(referenceEdge);

    }

    protected void addEdge(Edge edge) {
        edges.add(edge);
    }

    public boolean haveAssociationEdge(){
        for (Edge edge : edges) {
            return edge.isAssociationEdge();
        }

        return false;
//        if (associationEdge == null) {
//            return false;
//        } else {
//            return true;
//        }
    }


//
//    public Name selfCopy(Scope newScope) {
//        Name newName = new Name(variableName, variableId);
//
//        if (declarationEdge != null) {
//            newName.setDeclarationEdge(new DeclarationEdge(newScope, newName));
//        }
//
//        return newName;
//
//    }

    public String getVariableName() {
        return variableName;
    }

    public int getVariableId() {
        return variableId;
    }

    @Override
    public String toString() {
        return variableName + "" + variableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(variableName, name.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }

    public Edge getReferenceEdge() {
        for (Edge edge : edges) {
            if(edge.isReferenceEdge()){
                return edge;
            }
        }

        return null;
    }
}
