package Test;


import Model.Name;
import Model.Scope;

/**
 * @author Wenjie FU
 * @create 2021-06-30 14:48
 */
public class Test1 {
    public static void main(String[] args) {
        Scope scope1 = new Scope(1);
        Name nameB4 = scope1.constructDeclaration("B", 4);
        Name nameA1 = scope1.constructDeclaration("A", 1);

        Scope scope3 = new Scope(3);
        scope3.constructDirectEdge(scope1);
        scope3.constructDeclaration("b", 6);
        Name nameA5 = scope3.constructNominalEdge("A", 5);

        Scope scope2 = new Scope(2);
        scope2.constructDirectEdge(scope1);
        scope2.constructDeclaration("a", 2);

        nameB4.constructAssociation(scope3);
        nameA1.constructAssociation(scope2);

        Name namea7 = new Name("a", 7);
        namea7.constructReference(scope3);

        System.out.println(namea7.getReferenceEdges().get(0).getEnd().getDirectEdges().get(0).getEnd());

    }



}
