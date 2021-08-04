package test;

import model.scope.Scope;
import model.scope.ScopeGraph;
import org.junit.Test;

/**
 * @author Wenjie FU
 * @create 2021-07-22 21:48
 */
public class Test3ScopeGraphFuse {
    public ScopeGraph createScopeGraph1(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(new int[]{1,2});
        scopeGraph.makeDeclaration(1, "x", 1);
        scopeGraph.makeDirectEdge(2, 1);
        return scopeGraph;
    }

    public ScopeGraph createScopeGraph2(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(new int[]{2});
        scopeGraph.makeReference(2, "x", 2);
        scopeGraph.makeDeclaration(2, "y", 1);
        return scopeGraph;
    }

//    @Test
//    public void fuseScopeGraph1AndScopaGraph2() {
//        ScopeGraph scopeGraph1 = createScopeGraph1();
//        scopeGraph1.printDotForScopeGraph();
//
//        ScopeGraph scopeGraph2 = createScopeGraph2();
//        scopeGraph2.printDotForScopeGraph();
//
//
//        ScopeGraph fuse = scopeGraph1.fuse(scopeGraph2);
//        fuse.printDotForScopeGraph();
//
//    }
}
