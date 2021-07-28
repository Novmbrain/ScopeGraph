package model.scope;

import com.sun.javafx.css.Declaration;
import model.edge.*;
import model.searchresult.PathImpl;

import javax.swing.text.AbstractDocument;
import java.util.*;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:20
 */
public class Scope extends Node {

    private int scopeId;
    private List<Edge> edges;

    private Scope() {
        edges = new LinkedList<>();
    }

    @Override
    public boolean haveAssociationEdge() {
        return false;
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
        this.addEdge(declarationEdge);
        name.addEdge(declarationEdge);

        return name;
    }

    /**
     *
     * @param parentScope
     */
    protected void constructDirectEdge(Scope parentScope){
        //connect this with new scope with DirectEdge(childrenScope -> parentScope)
        DirectEdge directEdge = new DirectEdge(this, parentScope);
        this.addEdge(directEdge);

    }

    protected Name constructNominalEdge(String variableName, int variableId) {
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

        for(Edge edge : edges){
            stringBuilder.append(edge);
        }

        return stringBuilder.toString();
    }

    //搜索结束
    protected void checkReference(Name reference, SearchResult searchResult){

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

//        //search all import module in scope
//        for (NominalEdge nominalEdge : nominalEdges) {
//            Name importModule = nominalEdge.getEnd();
//
//            searchResult.addNodeToCurrentPath(importModule);//backtrack1 begin
//
//            //first check if the reference of this module has its declaration
//            SearchResult moduleSearchResult = new SearchResult();
//            checkImportModule(importModule, moduleSearchResult);
//            //It's possible that a reference of a module has multiple declarations
//            if (moduleSearchResult.pathNumber() != 0) {
//                List<PathImpl<Node>> pathImpls = moduleSearchResult.getAllPath();
//                for (PathImpl pathImpl : pathImpls) {
//                    //the last node in the path is the declaration of this module
//                    Name module = (Name) pathImpl.getLastNode();
//                    Scope end = module.getAssociationEdge().getEnd();
//
//                    searchResult.addNodeToCurrentPath(module);//backtrack2 begin
//
//                    end.checkReference(reference, searchResult);
//
//                    searchResult.removeLastNodeFromCurrentPath();//backtrack2 end
//                }
//            }
//            searchResult.removeLastNodeFromCurrentPath();//backtrack1 end
//        }

        //use recursion to search node in parentScope
        for (Edge edge : edges) {
            if(edge.isDirectEdge()){
                Node parentScope = edge.getEnd();
                if (parentScope != null) {
                    ((Scope)parentScope).checkReference(reference, searchResult);
                }
            }
        }

    }
//
//    /**
//     * For now, it only works: find a declaration and end the search
//     * @param module
//     * @param searchResult
//     */
//    protected void checkImportModule(Name module, SearchResult searchResult){
//        searchResult.addNodeToCurrentPath(this);
//
//        for (DeclarationEdge declarationEdge : declarationEdges) {
//            Name declaration = declarationEdge.getEnd();
//
//            //first check this declaration is a module
//            if (declaration.haveAssociationEdge()) {
//                if(module.equals(declaration)){
//                    searchResult.addNodeToCurrentPath(declaration);
//                    searchResult.addCurrentPath();
//                    searchResult.removeLastNodeFromCurrentPath();
//                }
//            }
//        }
//
//        //use recursion to search parentScope
//        if(this.getDirectEdge() != null){
//            Scope parentScope = directEdge.getEnd();
//            parentScope.checkImportModule(module, searchResult);
//        }
//
//    }







//
//    protected Scope selfCopy(HashMap<String, Name> newNameMap,  HashMap<Integer, Scope> newScopeMap){
//        if (newScopeMap.containsValue(new Scope(scopeId))) {
//            return newScopeMap.get(scopeId);
//        }
//
//        Scope newScope = new Scope(scopeId);
//        newScopeMap.put(scopeId, newScope);
//
//
//        for (DeclarationEdge declarationEdge : declarationEdges) {
//            Name name = declarationEdge.getEnd();
//
//            Name newName  = newScope.constructDeclaration(name.getVariableName(), name.getVariableId());
//
//            if (newNameMap.containsKey(newName.toString())) {
//                continue;
//            }
//
//            newNameMap.put(newName.toString(), newName);
//
//            if(name.haveAssociationEdge()){
//                if (newScopeMap.containsValue(name.getAssociationEdge().getEnd())) {
//                    newName.constructAssociation(newScopeMap.get(name.getAssociationEdge().getEnd().getScopeId()));
//                }{
//                    newName.constructAssociation(name.getAssociationEdge().getEnd().selfCopy(newNameMap, newScopeMap));
//                }
//            }
//        }
//
//        for (ReferenceEdge referenceEdge : referenceEdges) {
//            Name name = referenceEdge.getStart();
//
//            Name newName = new Name(name.getVariableName(), name.getVariableId());
//
//            if (newNameMap.containsValue(newName)) {
//                continue;
//            }
//
//            newNameMap.put(newName.toString(), newName);
//            newName.constructReference(newScope);
//
//        }
//
//        if (directEdge != null) {
//
//            if (!newScopeMap.containsValue(this.getDirectEdge().getEnd())) {
//                newScope.constructDirectEdge(this.getDirectEdge().getEnd().selfCopy(newNameMap, newScopeMap));
//            } else {
//                newScope.constructDirectEdge(newScopeMap.get(this.getDirectEdge().getEnd().getScopeId()));
//            }
//        }
//
//        for (NominalEdge nominalEdge : nominalEdges) {
//            Name name = nominalEdge.getEnd();
//
//            Name newName = newScope.constructNominalEdge(name.getVariableName(), name.getVariableId());
//
//            if (newNameMap.containsValue(newName)) {
//                continue;
//            }
//
//            newNameMap.put(newName.toString(), newName);
//        }
//
//        return newScope;
//    }



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
