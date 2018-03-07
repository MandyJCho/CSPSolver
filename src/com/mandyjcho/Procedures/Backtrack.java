package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.*;

public class Backtrack {
    private HashMap<Variable, List<Integer>> variableDomainMap;
    private List<Constraint> constraints;
    private boolean enforceFC;

    public Backtrack(HashMap<String, Variable> variableMapping, List<Constraint> constraints, boolean enforceFC) {
        variableDomainMap = new HashMap<>();
        for (Variable variable : variableMapping.values())
            variableDomainMap.put(variable, variable.getDomain());

        this.constraints = constraints;
        Heuristics.setConstraints(constraints);
        this.enforceFC = enforceFC;
    }

    //    • Whenever the solver needs to choose a value during the search process, apply the least
    //    constraining value heuristic. If more than one value remains after applying this heuristic,
    //    break ties by preferring smaller values.
    private List<Integer> orderDomain(Variable variable, List<Integer> domain) {
        domain.sort((a, b) -> {
            int hOfA = Heuristics.getLeastConstrainingValueScore(variable, a);
            int hOfB = Heuristics.getLeastConstrainingValueScore(variable, b);

            if (hOfA == hOfB) return a - b;

            return hOfB - hOfA;
        });

        return domain;
    }

    private boolean isConsistent(Variable variable, int value) {

        return true;
    }

    private void forwardCheck() {}

    public void solve() {
        solve(new Assignment(), variableDomainMap);
    }

    private boolean solve(Assignment assignment, HashMap<Variable, List<Integer>> unassignedVars) {
        if (unassignedVars.size() == 0) {
            assignment.setResult(true);
            System.out.println(assignment + " solution");
            return true;
        }

        Variable variable = Heuristics.getMostConstrainedVariable(unassignedVars.keySet(), assignment);
        List<Integer> domain = orderDomain(variable, unassignedVars.get(variable));
        unassignedVars.remove(variable);
        System.out.println(variable + " domain: " + domain.toString());
        Assignment nextAssignment = new Assignment(assignment);

        for (int value : domain) {
            if (isConsistent()) {
                nextAssignment.assign(variable, value);
                if (enforceFC) forwardCheck();

                if (solve(nextAssignment, unassignedVars)) return true;
                System.out.println(assignment + " failure");
                nextAssignment.remove(variable);
            }
        }

        // Print here
        System.out.println(assignment + " failure");
        return false;
    }

}
