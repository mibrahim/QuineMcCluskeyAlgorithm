package quinemccluskeyalgorithm;

import java.util.ArrayList;
import java.util.List;

public class TestQM {
    public static void main(String[] args) {
        List<Term> termList = new ArrayList<>();

        termList.add(new Term(new byte[]{1, 0, 1}));
        termList.add(new Term(new byte[]{1, 1, 0}));
        termList.add(new Term(new byte[]{1, 1, 1}));

        Formula formula = new Formula(termList);

        System.out.println(formula.toString());

        formula.reduceToPrimeImplicants();
        System.out.println(formula.toString());

        formula.reducePrimeImplicantsToSubset();
        System.out.println(formula.toString());
    }
}
