package model;

import model.edge.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class Scope extends Node {

    private int scopeId;
    private List<DeclarationEdge> declarationEdges;
    private List<ReferenceEdge> referenceEdges;
    private AssociationEdge associationEdge;
    private DirectEdge directEdge;
    private List<NominalEdge> nominalEdges;


    private Scope() {
        declarationEdges = new ArrayList<>();
        referenceEdges = new ArrayList<>();
        nominalEdges = new ArrayList<>();
    }

    public Scope(int scopeId) {
        this();
        this.scopeId = scopeId;
    }

    protected Name constructDeclaration(String variableName, int variableId){
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using declarationEdge(scope -> name)
        DeclarationEdge declarationEdge = new DeclarationEdge(this, name);
        declarationEdges.add(declarationEdge);
        name.setDeclarationEdge(declarationEdge);

        return name;
    }


    /**
     *
     * @param parentScope
     */
    protected void constructDirectEdge(Scope parentScope){
        //connect this with new scope with DirectEdge(childrenScope -> parentScope)
        directEdge = new DirectEdge(this, parentScope);

    }

    protected Name constructNominalEdge(String variableName, int variableId) {
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using nominalEdge(scope -> name)
        NominalEdge nominalEdge = new NominalEdge(this, name);
        nominalEdges.add(nominalEdge);
        name.setNominalEdge(nominalEdge);

        return name;
    }

    public int getScopeId() {
        return scopeId;
    }

    public void setScopeId(int scopeId) {
        this.scopeId = scopeId;
    }

    public List<DeclarationEdge> getDeclarationEdges() {
        return declarationEdges;
    }

    public void setDeclarationEdges(List<DeclarationEdge> declarationEdges) {
        this.declarationEdges = declarationEdges;
    }

    public List<ReferenceEdge> getReferenceEdges() {
        return referenceEdges;
    }

    public void setReferenceEdges(List<ReferenceEdge> referenceEdges) {
        this.referenceEdges = referenceEdges;
    }

    public AssociationEdge getAssociationEdge() {
        return associationEdge;
    }

    public void setAssociationEdge(AssociationEdge associationEdge) {
        this.associationEdge = associationEdge;
    }

    public DirectEdge getDirectEdge() {
        return directEdge;
    }

    public void setDirectEdge(DirectEdge directEdge) {
        this.directEdge = directEdge;
    }

    public List<NominalEdge> getNominalEdges() {
        return nominalEdges;
    }

    public void setNominalEdges(List<NominalEdge> nominalEdges) {
        this.nominalEdges = nominalEdges;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "scopeId=" + scopeId +
                '}';
    }
}
