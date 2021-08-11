package model.scope;

import model.edge.Edge;

import java.util.HashMap;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:19
 */
public class ScopeGraph {

    private HashMap<String, Name> nameMap;
    private HashMap<Integer, Scope> scopeMap;

    public ScopeGraph() {
        nameMap = new HashMap<>();
        scopeMap = new HashMap<>();

    }

    public ScopeGraph(HashMap<String, Name> nameMap, HashMap<Integer, Scope> scopeMap) {
        this.nameMap = nameMap;
        this.scopeMap = scopeMap;
    }

    /**
     * Scope is automatically generated according to the number of inputs, and ScopeId is incremented on the basis of
     * existing ScopeId. Scopeid starts at 1
     *
     * @param amount
     */
    public void generateScope(int amount) {
        if (amount <= 0) return;

        int size = scopeMap.size();

        for (int i = 1; i <= amount; i++) {
            scopeMap.put(size + i, new Scope(i));
        }

    }

    /**
     * Generate a Scope using the input array element as a scopeId
     *
     * @param intArray
     */
    public void generateScope(int[] intArray) {

        for (int scopeId : intArray) {
            scopeMap.put(scopeId, new Scope(scopeId));
        }

    }

    /**
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return Name
     */
    public Name makeDeclaration(int scopeId, String variableName, int variableId) {
//        if (outOfScopeId(scopeId)) return null;

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructDeclaration(variableName, variableId);
        nameMap.put(name.toString(), name);

        return name;
    }

    /**
     * @param childrenScopeId
     * @param parentScopeId
     */
    public void makeDirectEdge(int childrenScopeId, int parentScopeId) {

        Scope childrenScope = scopeMap.get(childrenScopeId);
        Scope parentScope = scopeMap.get(parentScopeId);

        childrenScope.constructDirectEdge(parentScope);
    }

    /**
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return Name
     */
    public Name makeNominalEdge(int scopeId, String variableName, int variableId) {

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructNominalEdge(variableName, variableId);
        name.constructReference(scope);

        nameMap.put(name.toString(), name);
        return name;
    }

    /**
     * @param declarationScopeId
     * @param variableName
     * @param variableId
     * @param associationScopeId
     * @return name
     */
    public Name makeAssociation(int declarationScopeId, String variableName, int variableId, int associationScopeId) {

        Scope declarationScope = scopeMap.get(declarationScopeId);
        Scope assotiationScope = scopeMap.get(associationScopeId);

        Name name = declarationScope.constructDeclaration(variableName, variableId);
        nameMap.put(name.toString(), name);

        name.constructAssociation(assotiationScope);

        return name;
    }

    /**
     * @param scopeId
     * @param variableName
     * @param variableId
     * @return name
     */
    public Name makeReference(int scopeId, String variableName, int variableId) {

        Scope scope = scopeMap.get(scopeId);

        Name name = new Name(variableName, variableId);
        nameMap.put(name.toString(), name);

        name.constructReference(scope);

        return name;
    }


    public void printDotForScopeGraph() {
        System.out.println("digraph first{\n rankdir=BT \n");

        for (Name name : nameMap.values()) {
            System.out.println(name + " " + "[shape = box]");
        }

        for (Scope scope : scopeMap.values()) {
            System.out.println(scope.printDot());
        }

        System.out.println("}");

    }

    /**
     * Passing in a reference to find the path from the reference to its corresponding definiton in the Scope Graph
     *
     * @param reference
     * @return
     */
    public SearchResult checkReference(Name reference) {

        SearchResult searchResult = new SearchResult();
        searchResult.addNodeToCurrentPath(reference);

        Edge referenceEdge = reference.getReferenceEdge();

        if (referenceEdge != null) {
            Node scope = referenceEdge.getEnd();
            ((Scope) scope).checkReference(reference, searchResult);
        }

        return searchResult;
    }

    /**
     * Passing in a importModule to find the path from the importModule to its corresponding definiton in the Scope Graph
     *
     * @param module
     * @return
     */
    public SearchResult checkImportModule(Name module) {

        SearchResult searchResult = new SearchResult();

        Node scope = module.getReferenceScope();
        if (scope != null) {
            searchResult.addNodeToCurrentPath(module);

            ((Scope) scope).checkImportModule(module, searchResult);
        }

        return searchResult;

    }

    public Name getName(String name) {
        if (name != null && name.length() > 0) {
            return nameMap.get(name);
        }

        return null;
    }

    public HashMap<String, Name> getNameMap() {
        return nameMap;
    }

    public HashMap<Integer, Scope> getScopeMap() {
        return scopeMap;
    }

    /**
     * Create a new Scope Graph that does not share data with the original Scope Graph
     *
     * @return
     */
    public ScopeGraph selfCopy() {
        HashMap<String, Name> newNameMap = new HashMap<>();
        HashMap<Integer, Scope> newScopeMap = new HashMap<>();


        for (Scope scope : scopeMap.values()) {
            scope.selfCopy(newNameMap, newScopeMap);
        }

        return new ScopeGraph(newNameMap, newScopeMap);

    }


}
