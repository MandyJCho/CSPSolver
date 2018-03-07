package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.*;

/**
 * Created by Mandy Cho :) on 3/4/18.
 */
public class Heuristics {
    static List<Constraint> constraints;

    public static void setConstraints (List<Constraint> constraints) {
        Heuristics.constraints = constraints;
    }

    static public Variable getMostConstrainedVariable(Set<Variable> variables, Assignment assignment) {
        // find smallest domain size
        int min = Integer.MAX_VALUE;
        for (Variable variable : variables) min = Math.min(min, variable.size());

        // filter based on domain size
        List<Variable> matches = new ArrayList<>();
        for(Variable variable : variables)
            if (variable.size() == min) matches.add(variable);

        // resolve ties
        return getMostConstrainingVariable(matches, assignment);
    }

    static private Variable getMostConstrainingVariable(List<Variable> variables, Assignment assignment) {
        if (variables.size() == 1) return variables.get(0);

        System.out.println("Contenders: " + variables.toString() );

        HashMap<Variable, Integer> constraintCount = new HashMap<>();
        for (Variable variable : variables)
            constraintCount.put(variable, 0);

        // count the constraints
        // TODO: disclude constraints with assignmed variables from count
        for(Constraint constraint : constraints) {
            if (assignment.isConstraintApplicable(constraint)) {
                for (Variable conVariable : constraint.getVariables())
                    if(constraintCount.containsKey(conVariable))
                        constraintCount.put(conVariable, constraintCount.get(conVariable) + 1);
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

    public static int getLeastConstrainingValueScore(Variable variable, int value) {
        int sum = 0;

        for (Constraint constraint : constraints)
            if (constraint.containsVariable(variable))
                sum += constraint.enforceOn(variable, value).size();

        return sum;
    }

}
