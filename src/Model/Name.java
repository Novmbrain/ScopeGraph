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
    private DeclarationEdge declarationEdge;
    private ReferenceEdge referenceEdge;
    private AssociationEdge associationEdge;
    private NominalEdge nominalEdge;


    public Name() {
    }

    public Name(String variableName, int variableId) {
        this.variableName = variableName;
        this.variableId = variableId;
    }

    public void constructAssociation(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        associationEdge = new AssociationEdge(this, scope);
        scope.setAssociationEdge(associationEdge);
    }

    public void constructReference(Scope scope) {
        //connect scope with node using referenceEdge(name -> scope)
        referenceEdge = new ReferenceEdge(this, scope);
        scope.getReferenceEdges().add(referenceEdge);

    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public DeclarationEdge getDeclarationEdge() {
        return declarationEdge;
    }

    public void setDeclarationEdge(DeclarationEdge declarationEdge) {
        this.declarationEdge = declarationEdge;
    }

    public ReferenceEdge getReferenceEdge() {
        return referenceEdge;
    }

    public void setReferenceEdge(ReferenceEdge referenceEdge) {
        this.referenceEdge = referenceEdge;
    }

    public AssociationEdge getAssociationEdge() {
        return associationEdge;
    }

    public void setAssociationEdge(AssociationEdge associationEdge) {
        this.associationEdge = associationEdge;
    }

    public NominalEdge getNominalEdge() {
        return nominalEdge;
    }

    public void setNominalEdge(NominalEdge nominalEdge) {
        this.nominalEdge = nominalEdge;
    }

    @Override
    public String toString() {
        return "Name{" +
                "variableName='" + variableName + '\'' +
                ", variableId=" + variableId +
                '}';
    }
}
