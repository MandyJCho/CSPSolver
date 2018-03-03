package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.HashMap;
import java.util.List;

/**
 * The type Backtrack.
 */
public class Backtrack {
    static private HashMap<String, Variable> variables;
    static private List<Constraint> constraints;
    static private boolean enforceFC;

    /**
     * Instantiates a new Backtrack.
     *
     * @param variables   the variables
     * @param constraints the constraints
     * @param enforceFC   the enforce fc
     */
    public Backtrack(HashMap<String, Variable> variables, List<Constraint> constraints, boolean enforceFC) {
        this.variables = variables;
        this.constraints = constraints;
        this.enforceFC = enforceFC;
    }

    /**
     * Gets most constraining variable whenever the solver needs to choose a variable while searching
     */
    private static Variable getMostConstrainingVariable() {
    //    • Whenever the solver needs to choose a variable during the search process, apply the most
    //    constrained variable heuristic, breaking ties using the most constraining variable heuristic.
    //    If more than one variable remains after applying these heuristics, break ties alphabetically.
    // count number of constraints
    // disclude any constraints with assigned variables
        return null;
    }
//    • Whenever the solver needs to choose a value during the search process, apply the least
//    constraining value heuristic. If more than one value remains after applying this heuristic,
//    break ties by preferring smaller values.

    /**
     * Gets least constraining value.
     */
    private static void getLeastConstrainingValue() {

    }

    private boolean isGoalState() {
        for (Variable v: variables.values())
            if (v.getAssignment() == null) return false;
        return true;
    }

    public void solve() {
        if (isGoalState()) System.out.print("\tsolution");
        Variable variable = getMostConstrainingVariable();

    }

}
