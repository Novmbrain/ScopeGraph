package Model;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void  GenerateScope(int amount){
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

    @Override
    public String toString() {
        return "ScopeGraph{" +
                "nameList=" + nameList +
                "\n scopeMap=" + scopeMap +
                '}';
    }
}
