package test;

import model.scope.Name;
import model.scope.ScopeGraph;

/**
 * @author Wenjie FU
 * @create 2021-07-20 17:13
 */
public class ScopeGraphCreater {

    /**
     * The article mentioned below is "A constraint Language for Static Semantic Analysis Based on Scope Graphs"
     */

    /**
     * The Scope Graph is Figure2 in the article
     * @return
     */
    public static ScopeGraph ScopeGraphFirgure2(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(1);

        Name referencex4 = scopeGraph.makeReference(1, "x", 4);
        scopeGraph.makeReference(1, "x", 8);

        scopeGraph.makeDeclaration(1, "x", 1);
        scopeGraph.makeDeclaration(1, "y", 3);

        return scopeGraph;

    }

    /**
     * The Scope Graph is on the left of Figure3 in the article
     * @return
     */
    public static ScopeGraph ScopeGrapFirgure3Left(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(2);

        Name referencef6 = scopeGraph.makeReference(2, "f", 6);
        scopeGraph.makeReference(2, "n", 7);
        scopeGraph.makeDeclaration(2, "n", 4);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(1, "f", 3);
        scopeGraph.makeDeclaration(1, "n", 1);

        return scopeGraph;

    }

    /**
     * The Scope Graph is on the right of Figure3 in the article
     * @return
     */
    public static ScopeGraph ScopeGraphFirgure3Right(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(3);

        scopeGraph.makeAssociation(1, "B", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);

        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(3, "b", 6);
        Name referencea7 = scopeGraph.makeReference(3, "a", 7);

        scopeGraph.makeNominalEdge(3, "A", 5);

        scopeGraph.makeDeclaration(2, "a", 2);

        return scopeGraph;
    }

    /**
     * This Scope Graph is modifed from ScopeGraphFIrgure3Right. I replace module B4 with module A4 to let the reference moduleA5 have two declaration(A4, A1)
     * @return
     */
    public  static ScopeGraph createScopeGraph4(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(3);

        scopeGraph.makeAssociation(1, "A", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);

        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(3, "b", 6);
        scopeGraph.makeReference(3, "a", 7);

        Name moduleA5 = scopeGraph.makeNominalEdge(3, "A", 5);

        scopeGraph.makeDeclaration(2, "a", 2);

        return scopeGraph;
    }
}
