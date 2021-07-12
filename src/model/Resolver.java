package model;

import model.edge.AssociationEdge;
import model.edge.DeclarationEdge;
import model.edge.DirectEdge;
import model.edge.NominalEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-12 17:18
 */
public class Resolver {
    private ArrayList<Name> nameList;
    private HashMap<Integer, Scope> scopeMap;

    public Resolver(ArrayList<Name> nameList, HashMap<Integer, Scope> scopeMap) {
        this.nameList = nameList;
        this.scopeMap = scopeMap;
    }

    public SearchResult checkReference(Name reference) {
        SearchResult searchResult = new SearchResult();

        searchResult.addPath(reference);

        //get scope
        Scope scope = reference.getReferenceEdge().getEnd();


        //search all declaration in scope
        if (helpCheckReference(reference, scope, searchResult) != null) {
            return searchResult;
        }



        return null;
    }

    private SearchResult helpCheckReference(Name reference, Scope scope, SearchResult searchResult) {
        searchResult.addPath(scope);
        //search all declaration in scope
        for (DeclarationEdge declarationEdge : scope.getDeclarationEdges()) {

            Name declaration = declarationEdge.getEnd();
            //first check this declaration is not a module
            if (declaration.getAssociationEdge() == null) {
                if (reference.equals(declaration)) {
                    searchResult.addPath(declaration);
                    searchResult.setResult(true);

                    return searchResult;
                }
            }
        }

        //search all import module in scope
        for (NominalEdge nominalEdge : scope.getNominalEdges()) {
            Name importModule = nominalEdge.getEnd();

            //backtrack begin
            searchResult.addPath(importModule);

            SearchResult moduleSearchResult = checkImportModule(importModule);
            if (moduleSearchResult != null) {
                List<Node> path = moduleSearchResult.getPath();
                Name module = (Name) path.get(path.size() - 1);
                Scope end = module.getAssociationEdge().getEnd();

                if (helpCheckReference(reference, end, searchResult) != null) {

                    return searchResult;
                }
            }
            //backtrack end
            searchResult.removeLastPath();

        }

        //use recursion to search all declaration in parentScope
        DirectEdge directEdge = scope.getDirectEdge();
        if (directEdge != null) {

            Scope parentScope = directEdge.getEnd();
            if (parentScope != null) {
                if (helpCheckReference(reference, parentScope, searchResult) != null) {
                    return searchResult;
                }
            }
        }



        return null;

    }

    public SearchResult checkImportModule(Name module) {
        Scope scope = module.getReferenceEdge().getEnd();
        Scope parentScope = scope.getDirectEdge().getEnd();

        if(parentScope == null) return null;

        SearchResult searchResult = new SearchResult();
        searchResult.addPath(module);
        searchResult.addPath(scope);

        if(helpCheckImportModule(module, parentScope, searchResult) != null){
            return searchResult;
        }

        return null;
    }

    private SearchResult helpCheckImportModule(Name module, Scope scope, SearchResult searchResult) {
        if(scope == null) {
            return null;
        }

        searchResult.addPath(scope);

        for (DeclarationEdge declarationEdge : scope.getDeclarationEdges()) {
            Name declaration = declarationEdge.getEnd();

            //first check this declaration is a module
            if (declaration.getAssociationEdge() != null) {
                if(module.equals(declaration)){
                    searchResult.addPath(declaration);
                    searchResult.setResult(true);
                    return searchResult;
                }

            }

        }

        //use recursion to search parentScope
        Scope parentScope = scope.getDirectEdge().getEnd();
        if (helpCheckImportModule(module, parentScope, searchResult) != null) {
            return searchResult;
        }

        return null;
    }
}


/**
 *
 * 构造一个搜索器，和一个专门的搜索结果类result
 *
 *
 * 搜索基本算法：
 * 需要验证的dependency：
 * reference，module，
 *
 * 对于reference，调用chekReference(Name reference)如果是module则不进行搜索
 *
 * 对于import，先调用Scope checkImport(parentScope)，如果找到返回对应的association scope，如果没找到返回null
 * 再调用moduleSearch(Scope parentScope)
 *
 * 不上UML图
 * @param name
 */