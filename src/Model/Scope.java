package Model;

import Model.Edge.*;

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
    private List<AssociationEdge> associationEdges;
    private List<DirectEdge> directEdges;
    private List<NominalEdge> nominalEdges;


    {
        declarationEdges = new ArrayList<>();
        referenceEdges = new ArrayList<>();
        associationEdges = new ArrayList<>();
        directEdges = new ArrayList<>();
        nominalEdges = new ArrayList<>();
    }

    public Scope() {
    }

    public Scope(int scopeId) {
        this.scopeId = scopeId;
    }

    public Name constructDeclaration(String variableName, int variableId){
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using declarationEdge(scope -> name)
        DeclarationEdge declarationEdge = new DeclarationEdge(this, name);
        declarationEdges.add(declarationEdge);
        name.getDeclarationEdges().add(declarationEdge);

        return name;
    }




    public void constructDirectEdge(Scope parentScope){
        //connect this with new scope with DirectEdge(childrenScope -> parentScope)
        DirectEdge directEdge = new DirectEdge(this, parentScope);
        directEdges.add(directEdge);
        parentScope.getDirectEdges().add(directEdge);

    }

    public Name constructNominalEdge(String variableName, int variableId) {
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using nominalEdge(scope -> name)
        NominalEdge nominalEdge = new NominalEdge(this, name);
        nominalEdges.add(nominalEdge);
        name.getNominalEdges().add(nominalEdge);

        return name;
    }

    public int getScopeId() {
        return scopeId;
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

    public List<DirectEdge> getDirectEdges() {
        return directEdges;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "scopeId=" + scopeId +
                '}';
    }
}
