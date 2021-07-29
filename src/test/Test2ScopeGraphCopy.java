package test;

import model.scope.Scope;
import model.scope.ScopeGraph;
import org.junit.Test;

/**
 * @author Wenjie FU
 * @create 2021-07-22 8:43
 */
    public class Test2ScopeGraphCopy {

    @Test
    public void test1(){
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure2();
        scopeGraph.printDotForScopeGraph();
        ScopeGraph copy = scopeGraph.selfCopy();
        copy.printDotForScopeGraph();

    }

    @Test
    public void test2(){
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGrapFirgure3Left();

        ScopeGraph copy = scopeGraph.selfCopy();
        copy.printDotForScopeGraph();

    }

    @Test
    public void test3(){
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();
        ScopeGraph copy = scopeGraph.selfCopy();
        copy.printDotForScopeGraph();
    }

}
