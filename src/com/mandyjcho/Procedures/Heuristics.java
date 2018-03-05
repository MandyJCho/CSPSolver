package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Mandy Cho :) on 3/4/18.
 */
public class Heuristics {

    static public Variable getMostConstrainedVariable(List<Variable> variables, List<Constraint> constraints) {
        // find smallest domain size
        int min = Integer.MAX_VALUE;
        for (Variable variable : variables) min = Math.min(min, variable.size());

        // filter based on domain size
        List<Variable> matches = new ArrayList<>();
        for(Variable variable : variables)
            if (variable.size() == min) matches.add(variable);

        // resolve ties
        return getMostConstrainingVariable(matches, constraints);
    }

    static private Variable getMostConstrainingVariable(List<Variable> variables, List<Constraint> constraints) {
        if (variables.size() == 1) return variables.get(0);

        HashMap<Variable, Integer> constraintCount = new HashMap<>();
        for (Variable variable : variables)
            constraintCount.put(variable, 0);

        // count the constraints
        for(Constraint constraint : constraints) {
            // if (constraint.isApplicable()) {
            for (Variable conVariable : constraint.getVariables())
                if(constraintCount.containsKey(conVariable))
                    constraintCount.put(conVariable, constraintCount.get(conVariable) + 1);
            //  }
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

    private int leastConstrainingValueScorer(Variable variable, int value) {
        HashSet<Variable> triedVariables = new HashSet<>();
        int sum = 0;

        // if there is a constraint with the variable, then add the reduced domain size
        // else add the domain size
        // keep track of variables
        return sum;
    }

}
