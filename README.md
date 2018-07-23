# QuineMcCluskeyAlgorithm
Java library that implements the Quine McCluskey Algorithm for finding prime implicants of a Boolean function

Forked from https://github.com/jgtz/QuineMcCluskeyAlgorithm

# Example

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
        
Output

        3 terms, 3 variables
        {1 0 1 }
        {1 1 0 }
        {1 1 1 }

        2 terms, 3 variables
        {1 X 1 }
        {1 1 X }

        2 terms, 3 variables
        {1 X 1 }
        {1 1 X }
