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
     * Test Scope Graph: The Scope Graph for the test is Figure2 in the article
     * Test purpose: Find Declaration X1 from Reference X4
     * Test Result: Sucess
     */
    @Test
    public void scopeGraph1(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(1);

        Name referencex4 = scopeGraph.makeReference(1, "x", 4);
        scopeGraph.makeReference(1, "x", 8);

        scopeGraph.makeDeclaration(1, "x", 1);
        scopeGraph.makeDeclaration(1, "y", 3);

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencex4));
    }


    /**
     * Test Scope Graph: The Scope Graph for the test is on the left of Figure3 in the article
     * Test purpose: Find Declaration f3 from Reference f6. Test if the algorithm recurses correctly
     * Test Result: Sucess
     */
    @Test
    public void scopeGraph2(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(2);

        Name referencef6 = scopeGraph.makeReference(2, "f", 6);
        scopeGraph.makeReference(2, "n", 7);
        scopeGraph.makeDeclaration(2, "n", 4);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(1, "f", 3);
        scopeGraph.makeDeclaration(1, "n", 1);

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencef6));
    }

    /**
     * Test Scope Graph: The Scope Graph for the test is on the left of Figure3 in the article
     * Test purpose: Find Declaration n4 and n1 from Reference n7. Test whether the algorithm can find multiple possible paths
     * Test Result: Sucess
     */
    @Test
    public void scopeGraph3(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(2);

        scopeGraph.makeReference(2, "f", 6);
        Name referencen7 = scopeGraph.makeReference(2, "n", 7);
        scopeGraph.makeDeclaration(2, "n", 4);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(1, "f", 3);
        scopeGraph.makeDeclaration(1, "n", 1);

        scopeGraph.printScopeGraph();

        scopeGraph.printScopeGraph();

        System.out.println(scopeGraph.checkReference(referencen7));
    }

    /**
     * Test Scope Graph: The Scope Graph for the test is on the right of Figure3 in the article
     * Test purpose: Find Declaration a2 from Reference a7. Test whether the corresponding declaration can be found in the Import Module
     * Test Result: Sucess
     */
    @Test
    public void scopeGraph4() {

        //Construct a Scope Graph
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

        scopeGraph.printDotForScopeGraph();

        System.out.println(scopeGraph.checkReference(referencea7));

    }

    /**
     * Test Scope Graph: The Scope Graph for the test is on the right of Figure3 in the article
     * Test purpose: Test when the reference corresponding declaration does not exist
     * Test Result: can't find the declaration
     */
    @Test
    public void scopeGraph5() {

        //Construct a Scope Graph
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(3);

        scopeGraph.makeAssociation(1, "B", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);

        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(3, "b", 6);
        scopeGraph.makeReference(3, "a", 7);

        scopeGraph.makeNominalEdge(3, "A", 5);

        scopeGraph.makeDeclaration(2, "a", 2);

        scopeGraph.printScopeGraph();

        Name testName = new Name("j", 111);

        System.out.println(scopeGraph.checkReference(testName));

    }
    /**
     * Test Scope Graph: The Scope Graph for the test is on the right of Figure3 in the article
     * Test purpose: Find Declaration A1 from ImportModule A5.
     * Test Result:
     */
    @Test
    public void scopeGraph6() {

        //Construct a Scope Graph
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(3);

        scopeGraph.makeAssociation(1, "B", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);

        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(3, "b", 6);
        scopeGraph.makeReference(3, "a", 7);

        Name moduleA5 = scopeGraph.makeNominalEdge(3, "A", 5);

        scopeGraph.makeDeclaration(2, "a", 2);

        scopeGraph.printScopeGraph();

        System.out.println(scopeGraph.checkImportModule(moduleA5));

    }

    /**
     * Test Scope Graph: A new Scope Graph
     * Test purpose: Find Declaration A1 from ImportModule A5 and A4. Test when the reference corresponding multiple declaration
     * Test Result:
     */
    @Test
    public void scopeGraph7() {

        //Construct a Scope Graph
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

        scopeGraph.printScopeGraph();

        System.out.println(scopeGraph.checkImportModule(moduleA5));

    }
}
