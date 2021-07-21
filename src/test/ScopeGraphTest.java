package test;

import model.Name;
import model.ScopeGraph;
import org.junit.Test;

/**

 * @author Wenjie FU
 * @create 2021-07-12 11:18
 *
 */
public class ScopeGraphTest {
    /**
     * Test purpose: Find Declaration X1 from Reference X4
     */
    @Test
    public void Test0(){

        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure2();

        scopeGraph.printDotForScopeGraph();

        Name referencex4 = scopeGraph.getName("x4");

        System.out.println(scopeGraph.checkReference(referencex4));
    }


    /**
     * Test purpose: Find Declaration f3 from Reference f6. Test if the algorithm recurses correctly
     */
    @Test
    public void Test1(){
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGrapFirgure3Left();

        Name referencef6 = scopeGraph.getName("f6");

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencef6));
    }

    /**
     * Test purpose: Find Declaration n4 and n1 from Reference n7. Test whether the algorithm can find multiple possible paths
     */
    @Test
    public void Test2(){
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGrapFirgure3Left();

        Name referencen7 = scopeGraph.getName("n7");

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencen7));
    }

    /**
     * Test purpose: Find Declaration a2 from Reference a7. Test whether the corresponding declaration can be found in the Import Module
     */
    @Test
    public void Test3() {
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();
        Name referencea7 = scopeGraph.getName("a7");

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencea7));

    }

    /**
     * Test purpose: Test when the reference corresponding declaration does not exist
     */
    @Test
    public void Test4() {
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();

        scopeGraph.printDotForScopeGraph();

        Name testName = new Name("j", 111);

        System.out.println(scopeGraph.checkReference(testName));

    }
    /**
     * Test purpose: Find Declaration A1 from ImportModule A5.
     */
    @Test
    public void Test5() {
        ScopeGraph scopeGraph = ScopeGraphCreater.ScopeGraphFirgure3Right();

        Name moduleA5 = scopeGraph.getName("A5");

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkImportModule(moduleA5));

    }

    /**

     * Test purpose: Find Reference A5 from Declaration A1 and A4. Test when the reference corresponding multiple declaration
     * Test Result:
     */
    @Test
    public void Test6() {
        ScopeGraph scopeGraph = ScopeGraphCreater.createScopeGraph4();

        Name moduleA5 = scopeGraph.getName("A5");

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkImportModule(moduleA5));

    }
}
