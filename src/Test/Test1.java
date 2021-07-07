package Test;


import Model.Name;
import Model.Scope;
import Model.ScopeGraph;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:48
 */
public class Test1 {
    public static void main(String[] args) {
        //Construct a Scope Graph
        ScopeGraph scopeGraph = new ScopeGraph();
        scopeGraph.GenerateScope(3);
        scopeGraph.makeAssociation(1, "B", 4, 3);
        scopeGraph.makeAssociation(1, "A", 1, 2);
        scopeGraph.makeDirectEdge(3, 1);
        scopeGraph.makeDirectEdge(2, 1);
        scopeGraph.makeDeclaration(3, "b", 6);
        Name namea7 = scopeGraph.makeReference(3, "a", 7);
        scopeGraph.makeNominalEdge(3, "A", 5);
        scopeGraph.makeNominalEdge(2, "a", 2);

        System.out.println(scopeGraph);
        //a7 -> scope3 -> scope1
        System.out.println(namea7.getReferenceEdge().getEnd().getDirectEdge().getEnd());
        //

        

//        //construct  scope1 and all node that scope1 points to
//        Scope scope1 = new Scope(1);
//        Name nameB4 = scope1.constructDeclaration("B", 4);
//        Name nameA1 = scope1.constructDeclaration("A", 1);
//
//        //construct  scope3 and all node that scope3 points to
//        Scope scope3 = new Scope(3);
//        scope3.constructDirectEdge(scope1);
//        scope3.constructDeclaration("b", 6);
//        Name nameA5 = scope3.constructNominalEdge("A", 5);
//
//        //construct  scope2 and all node that scope2 points to
//        Scope scope2 = new Scope(2);
//        scope2.constructDirectEdge(scope1);
//        scope2.constructDeclaration("a", 2);
//
//        //Tow Association
//        nameB4.constructAssociation(scope3);
//        nameA1.constructAssociation(scope2);
//
//        //One reference
//        Name namea7 = new Name("a", 7);
//        namea7.constructReference(scope3);

    }



}
