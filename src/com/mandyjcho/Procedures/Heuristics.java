package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.*;

/**
 * Created by Mandy Cho :) on 3/4/18.
 */
public class Heuristics {
    private static List<Constraint> constraints;

    public static void setConstraints (List<Constraint> constraints) {
        Heuristics.constraints = constraints;
    }

    static public Variable getMostConstrainedVariable(HashMap<Variable, List<Integer>> unassignedVars, Assignment assignment) {
        int min = Integer.MAX_VALUE;
        for (Variable variable : unassignedVars.keySet())
            min = Math.min(min, unassignedVars.get(variable).size());

        List<Variable> matches = new ArrayList<>();
        for(Variable variable : unassignedVars.keySet())
            if (unassignedVars.get(variable).size() == min) matches.add(variable);

        return getMostConstrainingVariable(matches, assignment);
    }

    static private Variable getMostConstrainingVariable(List<Variable> variables, Assignment assignment) {
        if (variables.size() == 1) return variables.get(0);

        HashMap<Variable, Integer> constraintCount = new HashMap<>();
        for (Variable variable : variables)
            constraintCount.put(variable, 0);

        for(Constraint constraint : constraints) {
            if (assignment.isConstraintApplicable(constraint)) {
                for (Variable constraintVar : constraint.getVariables())
                    if(constraintCount.containsKey(constraintVar))
                        constraintCount.put(constraintVar, constraintCount.get(constraintVar) + 1);
            }
        }

        Variable bestChoice = null;
        int max = -1;
        for(Variable variable : constraintCount.keySet()) {
            int count = constraintCount.get(variable);
            if (count > max || (count == max && bestChoice.compareTo(variable) > 0)) bestChoice = variable;
            max = Math.max(max, count);
        }

        return bestChoice;
    }

    public static int getLeastConstrainingValueScore(Variable variable, int value, HashMap<Variable, List<Integer>> unassignedVars) {
        int sum = 0;

        for (Constraint constraint : constraints)
            if (constraint.contains(variable))
                sum += constraint.enforceOn(variable, value, unassignedVars).size();

        return sum;
    }

}
