package model;

import model.edge.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public class ScopeGraph {

    private ArrayList<Name> nameList;
    private HashMap<Integer, Scope> scopeMap;

    public ScopeGraph() {
        nameList = new ArrayList<>();
        scopeMap = new HashMap<>();

    }


    /**
     * Scope is automatically generated according to the number of inputs, and ScopeId is incremented on the basis of
     * existing ScopeId. Scopeid starts at 1
     * @param amount
     */
    public void  generateScope(int amount){
        if(amount <= 0) return;

        int size = scopeMap.size();

        for(int i = 1; i <= amount; i++){
            scopeMap.put(size + i, new Scope(i));
        }

    }

    /**
     *
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return
     */
    public Name makeDeclaration(int scopeId, String variableName, int variableId){
        if(outOfScopeId(scopeId)) return null;

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructDeclaration(variableName, variableId);
        nameList.add(name);

        return name;
    }

    /**
     *
     * @param childrenScopeId
     * @param parentScopeId
     */
    public void makeDirectEdge(int childrenScopeId, int parentScopeId){
        if(outOfScopeId(childrenScopeId) || outOfScopeId(parentScopeId) || childrenScopeId == parentScopeId) return;

        Scope childrenScope = scopeMap.get(childrenScopeId);
        Scope parentScope = scopeMap.get(parentScopeId);

        childrenScope.constructDirectEdge(parentScope);
    }

    /**
     *
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return
     */
    public Name makeNominalEdge(int scopeId, String variableName, int variableId){
        if(outOfScopeId(scopeId)) return null;

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructNominalEdge(variableName, variableId);
        name.constructReference(scope);

        nameList.add(name);
        return name;
    }

    /**
     *
     * @param declarationScopeId
     * @param variableName
     * @param variableId
     * @param associationScopeId
     */
    public Name makeAssociation(int declarationScopeId, String variableName, int variableId, int associationScopeId){
        if (outOfScopeId(declarationScopeId) || outOfScopeId(associationScopeId) || declarationScopeId == associationScopeId)
            return null;

        Scope declarationScope = scopeMap.get(declarationScopeId);
        Scope assotiationScope = scopeMap.get(associationScopeId);

        Name name = declarationScope.constructDeclaration(variableName, variableId);
        nameList.add(name);

        name.constructAssociation(assotiationScope);

        return name;
    }

    /**
     *
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return
     */
    public Name makeReference(int scopeId, String variableName, int variableId) {
        if(outOfScopeId(scopeId)) return null;

        Scope scope = scopeMap.get(scopeId);

        Name name = new Name(variableName, variableId);
        nameList.add(name);

        name.constructReference(scope);

        return name;
    }



    private boolean outOfScopeId(int scopeId){
        if(scopeId > scopeMap.size() || scopeId <= 0 || scopeMap.isEmpty()){
            return true;
        } else{
            return false;
        }

    }

    private String printScope(Scope scope) {

        int scopeId = scope.getScopeId();
        List<DeclarationEdge> declarationEdges = scope.getDeclarationEdges();
        List<ReferenceEdge> referenceEdges = scope.getReferenceEdges();
        AssociationEdge associationEdge = scope.getAssociationEdge();
        DirectEdge directEdge = scope.getDirectEdge();
        List<NominalEdge> nominalEdges = scope.getNominalEdges();


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Scope" + scopeId + " :\n\n");

        if (!declarationEdges.isEmpty()) {
            for (DeclarationEdge declarationEdge : declarationEdges) {
                stringBuilder.append("Declaration : ---> " + declarationEdge.getEnd() + "\n");
            }
        }

        if (!referenceEdges.isEmpty()) {
            for (ReferenceEdge referenceEdge : referenceEdges) {
                stringBuilder.append("Reference : <--- " + referenceEdge.getStart() + "\n");
            }
        }

        if (associationEdge != null) {
            stringBuilder.append("Association : <--- " + associationEdge.getStart() + "\n");
        }


        if (directEdge != null) {
            stringBuilder.append("DirectEdge : ---> " + directEdge.getEnd() + "\n");
        }


        if (!nominalEdges.isEmpty()) {
            for (NominalEdge nominalEdge : nominalEdges) {
                stringBuilder.append("NominalEdge : ---> " + nominalEdge.getEnd());
            }
        }

        return stringBuilder.toString();
    }

    public void printScopeGraph() {
        Collection<Scope> scopeList = scopeMap.values();

        for (Scope scope : scopeList) {
            System.out.println(printScope(scope));
        }

    }

    public SearchResult checkReference(Name reference) {

        SearchResult searchResult = new SearchResult();
        searchResult.addNodeToPath(reference);

        //get scope
        if(reference.getReferenceEdge() != null){
            //search all declaration in scope
            Scope scope =reference.getReferenceEdge().getEnd();
            scope.checkReference(reference, searchResult);

        }

        return searchResult;
    }

    public SearchResult checkImportModule(Name module) {

        SearchResult searchResult = new SearchResult();

        if(module.getReferenceEdge() != null){
            Scope scope = module.getReferenceEdge().getEnd();

            if(scope == null) return searchResult;

            searchResult.addNodeToPath(module);
            searchResult.addNodeToPath(scope);

            scope.checkImportModule(module, searchResult);
        }


        return searchResult;

    }

}
