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

    /**
     * Constructs an empty ScopeGraph
     */
    public ScopeGraph() {
        nameMap = new HashMap<>();
        scopeMap = new HashMap<>();

    }

    /**
     * Constructs a ScopeGraph containing the elements of the specified map
     * @param nameMap the HashMap whose mapppings are to be placed in this Scope Graph. This map contains Scope Graph's name
     * @param scopeMap the HashMap whose mappings are to be placed int this Scope Graph. This map contains Scope Graph's scope
     */
    public ScopeGraph(HashMap<String, Name> nameMap, HashMap<Integer, Scope> scopeMap) {
        this.nameMap = nameMap;
        this.scopeMap = scopeMap;
    }

    /**
     * Generate n scopes, n == amount. ScopeId will increment on the basis of existing ScopeId. Scopeid starts at 1
     * <p>
     * Example: Generate a Scope Graph with five scopes
     * <p>
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = new ScopeGraph();
     *      scopeGraph.generateScope(5);
     *     }
     * </pre>
     *
     *
     * @param amount the number of Scope
     */
    public void generateScope(int amount) {
        if (amount <= 0) return;

        int size = scopeMap.size();

        for (int i = 1; i <= amount; i++) {
            scopeMap.put(size + i, new Scope(i));
        }

    }

    /**
     * Generate some Scopes using the input array element as a scopeId
     * <p>
     * Example: Use 1, 2, and 3 as scopeId to generate three Scope graphs
     * <p>
     * <pre>
     *      {@code
     *       ScopeGraph scopeGraph = new ScopeGraph();
     *       scopeGraph.generateSope(new in[]{1,2,3});
     *      }
     * </pre>
     *
     * @param intArray the array whose elements are to be used as scopeId
     */
    public void generateScope(int[] intArray) {

        for (int scopeId : intArray) {
            scopeMap.put(scopeId, new Scope(scopeId));
        }

    }

    /**
     * Establish a DeclrationEdge between a Scope and a Declaration
     *
     * Example: Add Declaration x1 to Scope1
     * <pre>
     *      {@code
     *       ScopeGraph scopeGraph = new ScopeGraph();
     *       scopeGraph.generateScope(5);
     *       scopeGraph.makeDeclaration(1, "x", 1);
     *      }
     * </pre>
     *
     * @param scopeId the scopeId of the Scope that want to create the Edge
     * @param variableName Declaration's name
     * @param variableId Declaration's Id
     * @return the Declaration
     */
    public Name makeDeclaration(int scopeId, String variableName, int variableId) {

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructDeclaration(variableName, variableId);
        nameMap.put(name.toString(), name);

        return name;
    }

    /**
     * Establish a DeclrationEdge between a Parent Scope and a Children Scope
     *
     * Example: establish DirectEdge between Scope 1 and Scope 2
     *
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = new ScopeGraph();
     *      scopeGraph.generateScope(5);
     *      scopeGraph.makeDirectEdge(2, 1);
     *     }
     * </pre>
     *
     * @param childrenScopeId parent's scopeId
     * @param parentScopeId children's scopeId
     */
    public void makeDirectEdge(int childrenScopeId, int parentScopeId) {

        Scope childrenScope = scopeMap.get(childrenScopeId);
        Scope parentScope = scopeMap.get(parentScopeId);

        childrenScope.constructDirectEdge(parentScope);
    }

    /**
     * Firstly, establishs a NominalEdge between a Scope and its Reference. Then, creates a ReferenceEdge
     * between the Scope and the Reference Automaticallly
     *
     * Example: Add Reference A5 to the Scope3
     *  <pre>
     *     {@code
     *      ScopeGraph scopeGraph = new ScopeGraph();
     *      scopeGraph.generateScope(5);
     *      Name moduleA5 = scopeGraph.makeNominalEdge(3, "A", 5);
     *     }
     *  </pre>
     *
     * @param scopeId Scope's Id
     * @param variableName Reference's name
     * @param variableId Reference's name
     * @return the Reference
     */
    public Name makeNominalEdge(int scopeId, String variableName, int variableId) {

        Scope scope = scopeMap.get(scopeId);
        Name name = scope.constructNominalEdge(variableName, variableId);
        name.constructReference(scope);

        nameMap.put(name.toString(), name);
        return name;
    }

    /**
     * Firstly, establishs a DeclarationEdge between a Scope and its Declaration. Then creates a AssociationEdge
     * bewtween the Decalration and its associated Scope.
     *
     * Example: Adds Declaration B4 to the Scope1 and associates Scope3 to the Declaration B4
     *
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = new ScopeGraph();
     *      scopeGraph.generateScope(5);
     *      scopeGraph.makeAssociation(1, "B", 4, 3);
     *     }
     * </pre>
     *
     * @param declarationScopeId the scopeId of the Scope that has Declaration
     * @param variableName Declaration's name
     * @param variableId Declaration's Id
     * @param associationScopeId the scopeId of the Scope that will be assciated
     * @return the Declaration
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
     * Establishs a ReferenceEdge between a Scope and its Reference
     *
     * Example: Adds the Reference x4 to Scope1
     *
     * <pre>
     *    {@code
     *     ScopeGraph scopeGraph = new ScopeGraph();
     *     scopeGraph.generateScope(1);
     *     scopeGraph.makeReference(1, "x", 4);
     *    }
     * </pre>
     *
     * @param scopeId the scopeId of the Scope that has Reference
     * @param variableName Reference's name
     * @param variableId Reference's Id
     * @return the Reference
     */
    public Name makeReference(int scopeId, String variableName, int variableId) {

        Scope scope = scopeMap.get(scopeId);

        Name name = new Name(variableName, variableId);
        nameMap.put(name.toString(), name);

        name.constructReference(scope);

        return name;
    }

    /**
     * Print Scope Graph in Dot language on the console
     *
     * Example:
     *
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure2();
     *      scopeGraph.printDotForScopeGraph();
     *     }
     * </pre>
     *
     */
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
     * Passing in a reference to find the path from the Reference to its corresponding Definiton in the Scope Graph
     *
     * <pre>
     *     {@code
     *       ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGrapFirgure3Left();
     *       Name referencen7 = scopeGraph.getName("n7");
     *       System.out.println(scopeGraph.checkReference(referencen7));
     *     }
     *
     * </pre>
     * @param reference
     * @return a SearchResult which stored all of the possible paths.
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
     * <pre>
     *     {@code
     *       ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();
     *       Name moduleA5 = scopeGraph.getName("A5");
     *       System.out.println(scopeGraph.checkImportModule(moduleA5));
     *     }
     *
     * </pre>
     * @param module
     * @return  a SearchResult which stored all of the possible paths.
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

    /**
     * Gets the specific Name in Scope Graph by it's "variableName + variableId"
     *
     * Example:
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();
     *      Name moduleA5 = scopeGraph.getName("A5");
     *     }
     * </pre>
     * @param name
     * @return the Name or Null
     */
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
     * <pre>
     *     {@code
     *      ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGrapFirgure3Left();
     *      ScopeGraph copy = scopeGraph.selfCopy();
     *      copy.printDotForScopeGraph();
     *     }
     * </pre>
     *
     * @return the new ScopeGraph
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
