package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Backtrack.
 */
public class Backtrack {
    static private HashMap<String, Variable> variableMapping;
    static private List<Constraint> constraints;
    static private boolean enforceFC;

    /**
     * Instantiates a new Backtrack.
     *
     * @param variableMapping   the variables
     * @param constraints the constraints
     * @param enforceFC   the enforce fc
     */
    public Backtrack(HashMap<String, Variable> variableMapping, List<Constraint> constraints, boolean enforceFC) {
        this.variableMapping = variableMapping;
        this.constraints = constraints;
        this.enforceFC = enforceFC;
    }

    // Whenever the solver needs to choose a variable during the search process
    private Variable getMostConstrainedVariable(List<Variable> variables) {
        // find smallest domain size
        int min = Integer.MAX_VALUE;
        for (Variable variable : variables) min = Math.min(min, variable.size());

        // filter based on domain size
        List<Variable> matches = new ArrayList<>();
        for(Variable variable : variables)
            if (variable.size() == min) matches.add(variable);

        // resolve ties
        return getMostConstrainingVariable(matches);
    }

    private Variable getMostConstrainingVariable(List<Variable> variables) {
        if (variables.size() == 1) return variables.get(0);

        HashMap<Variable, Integer> constraintCount = new HashMap<>();
        for (Variable variable : variables)
            constraintCount.put(variable, 0);

        // count the constraints
        for(Constraint constraint : constraints) {
            if (constraint.isApplicable()) {
                for (Variable conVariable : constraint.getVariables())
                    if(constraintCount.containsKey(conVariable))
                        constraintCount.put(conVariable, constraintCount.get(conVariable) + 1);
            }
        }

        Variable bestChoice = null;
        int max = -1;
        for(Variable variable : constraintCount.keySet()) {
            int count = constraintCount.get(variable);
            if (count < max || (count == max && bestChoice.compareTo(variable) < 0)) bestChoice = variable;
            max = Math.max(max, count);
        }

        return bestChoice;
    }

    //

//    • Whenever the solver needs to choose a value during the search process, apply the least
//    constraining value heuristic. If more than one value remains after applying this heuristic,
//    break ties by preferring smaller values.

    /**
     * Gets least constraining value.
     */
    private int getLeastConstrainingValue(Variable variable) {
        int bestChoice = -1, max = 0;

        return bestChoice;
    }

    private static  boolean isGoalState(List<Variable> variables) {
        for (Variable v: variables)
            if (v.getAssignment() == null) return false;
        return true;
    }

    public void solve() {
        solve(new ArrayList<>(variableMapping.values()));
    }

    private void solve(List<Variable> variables) {

        if (isGoalState(variables)) System.out.print("\tsolution");

        Variable variable = getMostConstrainedVariable(variables);
        System.out.println(variable);

        /*
        // Remove assigned variables
        variables = variables.stream()
                             .filter(variable -> variable.getAssignment() == null)
                             .collect(Collectors.toList());

        * */

    }

}
