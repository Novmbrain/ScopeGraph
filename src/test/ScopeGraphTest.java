package test;

import model.Name;
import model.Resolver;
import model.ScopeGraph;
import org.junit.Test;

/**
 * @author Wenjie FU
 * @create 2021-07-12 11:18
 */
public class ScopeGraphTest {
    @Test
    public void scopeGraph1(){
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.generateScope(1);

        Name referencex4 = scopeGraph.makeReference(1, "x", 4);
        scopeGraph.makeReference(1, "x", 8);

        scopeGraph.makeDeclaration(1, "x", 1);
        scopeGraph.makeDeclaration(1, "y", 3);

        scopeGraph.printScopeGraph();

        Resolver resolver = scopeGraph.getResolver();

        System.out.println(resolver.checkReference(referencex4));
    }

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

        scopeGraph.printScopeGraph();

        scopeGraph.printScopeGraph();

        Resolver resolver = scopeGraph.getResolver();

        System.out.println(resolver.checkReference(referencef6));
    }

    @Test
    public void scopeGraph3() {

        //Construct a Scope Graph
        ScopeGraph scopeGraph = new ScopeGraph();

        scopeGraph.generateScope(3);

        scopeGraph.makeAssociation(1, "B", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);

        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);

        scopeGraph.makeDeclaration(3, "b", 6);
        Name referencea7 = scopeGraph.makeReference(3, "a", 7);

        Name moduleA5 = scopeGraph.makeNominalEdge(3, "A", 5);

        scopeGraph.makeDeclaration(2, "a", 2);

        scopeGraph.printScopeGraph();

        Resolver resolver = scopeGraph.getResolver();

        System.out.println(resolver.checkReference(referencea7));
    }
}
