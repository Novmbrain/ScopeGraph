package Model;

import Model.Edge.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public class Name extends Node{

    private String variableName;
    private int variableId;
    private List<DeclarationEdge> declarationEdges;
    private List<ReferenceEdge> referenceEdges;
    private List<AssociationEdge> associationEdges;
    private List<NominalEdge> nominalEdges;

    {
        declarationEdges = new ArrayList<>();
        referenceEdges = new ArrayList<>();
        associationEdges = new ArrayList<>();
        nominalEdges = new ArrayList<>();
    }


    public Name() {
    }

    public Name(String variableName, int variableId) {
        this.variableName = variableName;
        this.variableId = variableId;
    }

    public String getVariableName() {
        return variableName;
    }

    public void constructAssociation(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        AssociationEdge associationEdge = new AssociationEdge(this, scope);
        associationEdges.add(associationEdge);
        scope.getAssociationEdges().add(associationEdge);

    }

    public void constructReference(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        ReferenceEdge referenceEdge = new ReferenceEdge(this, scope);
        referenceEdges.add(referenceEdge);
        scope.getReferenceEdges().add(referenceEdge);

    }

    public int getVariableId() {
        return variableId;
    }

    public List<DeclarationEdge> getDeclarationEdges() {
        return declarationEdges;
    }

    public List<ReferenceEdge> getReferenceEdges() {
        return referenceEdges;
    }

    public List<AssociationEdge> getAssociationEdges() {
        return associationEdges;
    }

    public List<NominalEdge> getNominalEdges() {
        return nominalEdges;
    }

    @Override
    public String toString() {
        return "Name{" +
                "variableName='" + variableName + '\'' +
                ", variableId=" + variableId +
                '}';
    }
}
