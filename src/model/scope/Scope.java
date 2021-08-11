package model.scope;

import model.edge.*;
import model.searchresult.PathImpl;

import java.util.*;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class Scope extends Node {

    private int scopeId;

    private Scope() {
        edges = new LinkedList<>();
    }

    public Scope(int scopeId) {
        this();
        this.scopeId = scopeId;
    }

    public Name constructDeclaration(String variableName, int variableId) {
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using declarationEdge(scope -> name)
        DeclarationEdge declarationEdge = new DeclarationEdge(this, name);
        this.addEdge(declarationEdge);
        name.addEdge(declarationEdge);

        return name;
    }


    public void constructDirectEdge(Scope parentScope) {
        //connect this with new scope with DirectEdge(childrenScope -> parentScope)
        DirectEdge directEdge = new DirectEdge(this, parentScope);
        this.addEdge(directEdge);

    }

    public Name constructNominalEdge(String variableName, int variableId) {
        //construct new node
        Name name = new Name(variableName, variableId);

        //connect scope with node using nominalEdge(scope -> name)
        NominalEdge nominalEdge = new NominalEdge(this, name);
        this.addEdge(nominalEdge);
        name.addEdge(nominalEdge);

        return name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String printDot() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Edge edge : edges) {
            stringBuilder.append(edge);
        }

        return stringBuilder.toString();
    }


    protected void checkReference(Name reference, SearchResult searchResult) {

        searchResult.addNodeToCurrentPath(this);


//       search all declaration in scope
        for (Edge edge : edges) {

            Node declaration = edge.getEnd();

            searchResult.addNodeToCurrentPath(declaration);
            //first check this declaration is not a module
            if (!declaration.haveAssociationEdge()) {
                if (reference.equals(declaration)) {
                    searchResult.addCurrentPath();
                }
            }

            searchResult.removeLastNodeFromCurrentPath();
        }

        //search all import module in scope
        for (Edge edge : edges) {

            if (edge.isNominalEdge()) {

                Node importModule = edge.getEnd();

                searchResult.addNodeToCurrentPath(importModule);//backtrack1 begin

                //first check if the reference of this module has its declaration
                SearchResult moduleSearchResult = new SearchResult();
                checkImportModule(importModule, moduleSearchResult);

                //It's possible that a reference of a module has multiple declarations
                if (moduleSearchResult.pathNumber() != 0) {
                    List<PathImpl<Node>> pathImpls = moduleSearchResult.getAllPath();
                    for (PathImpl pathImpl : pathImpls) {
                        //the last node in the path is the declaration of this module
                        Name module = (Name) pathImpl.getLastNode();

//                        Scope end = module.getAssociationEdge().getEnd();
                        if (module.getAssociationEdge() != null) {
                            Edge associationEdge = module.getAssociationEdge();
                            searchResult.addNodeToCurrentPath(module);//backtrack2 begin

                            Node scope = associationEdge.getEnd();
                            ((Scope) scope).checkReference(reference, searchResult);

                            searchResult.removeLastNodeFromCurrentPath();//backtrack2 end

                        }

                    }
                }
                searchResult.removeLastNodeFromCurrentPath();//backtrack1 end
            }

        }

        //use recursion to search node in parentScope
        for (Edge edge : edges) {
            if (edge.isDirectEdge()) {
                Node parentScope = edge.getEnd();
                if (parentScope != null) {
                    ((Scope) parentScope).checkReference(reference, searchResult);
                }
            }
        }

    }

    /**
     * For now, it only works: find a declaration and end the search
     *
     * @param module
     * @param searchResult
     */
    protected void checkImportModule(Node module, SearchResult searchResult) {
        searchResult.addNodeToCurrentPath(this);

        for (Edge edge : edges) {
            if (edge.isDeclarationEdge()) {
                Node declaration = edge.getEnd();
                //first check this declaration is a module
                if (declaration.haveAssociationEdge()) {
                    if (module.equals(declaration)) {
                        searchResult.addNodeToCurrentPath(declaration);
                        searchResult.addCurrentPath();
                        searchResult.removeLastNodeFromCurrentPath();
                    }
                }
            }
        }

        for (Edge edge : edges) {
            if (edge.isDirectEdge()) {
                Node parentScope = edge.getEnd();
                ((Scope) parentScope).checkImportModule(module, searchResult);
            }
        }

    }


    public Scope selfCopy(HashMap<String, Name> newNameMap, HashMap<Integer, Scope> newScopeMap) {
        if (newScopeMap.containsValue(new Scope(scopeId))) {
            return newScopeMap.get(scopeId);
        }

        Scope newScope = new Scope(scopeId);
        newScopeMap.put(scopeId, newScope);

        for (Edge edge : edges) {
            edge.selfCopy(newNameMap, newScopeMap, this, newScope);
        }


        return newScope;
    }


    public int getScopeId() {
        return scopeId;
    }

    @Override
    public String toString() {
        return "" + scopeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scope scope = (Scope) o;
        return scopeId == scope.scopeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scopeId);
    }


}
