package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.time.temporal.ValueRange;
import java.util.*;

public class Backtrack {
    private HashMap<Variable, List<Integer>> variableDomainMap;
    private List<Constraint> constraints;
    private boolean enforceFC;
    private int count = 0;

    public Backtrack(HashMap<String, Variable> variableMapping, List<Constraint> constraints, boolean enforceFC) {
        variableDomainMap = new HashMap<>();
        for (Variable variable : variableMapping.values())
            variableDomainMap.put(variable, variable.getDomain());

        this.constraints = constraints;
        Heuristics.setConstraints(constraints);
        this.enforceFC = enforceFC;
    }

    private List<Integer> orderDomain(Variable variable, HashMap<Variable, List<Integer>> unassignedVars) {
        HashMap<Integer, Integer> evaluations = new HashMap<>();
        List<Integer> domain = unassignedVars.get(variable);

        for(int value : domain)
            evaluations.put(value, Heuristics.getLeastConstrainingValueScore(variable, value, unassignedVars));

        domain.sort((a, b) -> {
            int hOfA = evaluations.get(a), hOfB = evaluations.get(b);
            if (hOfA == hOfB) return a - b;

            return hOfB - hOfA;
        });

        return domain;
    }

    private boolean isConsistent(Variable selection, int value, Assignment assignment, HashMap<Variable, List<Integer>> unassignedVars) {
        HashMap<Variable, List<Integer>> variables = new HashMap<>(unassignedVars);
        variables.putAll(assignment.getFormattedSolution());

        for(Constraint constraint : constraints)
            if (constraint.contains(selection) && constraint.enforceOn(selection, value, variables).size() == 0)
                return false;

        return true;
    }

    private void forwardCheck(Variable variable, int value, HashMap<Variable, List<Integer>> unassignedVars) {
        // parameters should receive unassigned variables, variable itself and the value
        // constraints with enforcer still unassigned should get propagated
        for(Constraint constraint : constraints) {
            Variable other = constraint.getOther(variable);
            if (other != null && unassignedVars.containsKey(other))
                unassignedVars.put(other, constraint.enforceOn(variable, value, unassignedVars));
        }
    }

    public void solve() {
        solve(new Assignment(), variableDomainMap);
    }

    private boolean solve(Assignment assignment, HashMap<Variable, List<Integer>> unassignedVars) {
        if (unassignedVars.size() == 0) {
            System.out.println(++count + "." + assignment + "  solution");
            return true;
        }

        Variable variable = Heuristics.getMostConstrainedVariable(unassignedVars, assignment);
        List<Integer> domain = orderDomain(variable, unassignedVars);
        unassignedVars.remove(variable);
        Assignment nextAssignment = new Assignment(assignment);
        for (int value : domain) {
            nextAssignment.assign(variable, value);
            if (isConsistent(variable, value, assignment, unassignedVars)) {
                if (enforceFC) forwardCheck(variable, value, unassignedVars);
                if (solve(nextAssignment, unassignedVars)) return true;
            }
            count++;
            System.out.println(count + "." + nextAssignment + "  failure");
            nextAssignment.remove(variable);
            if (count == 30) System.exit(0);
        }

        count++;
        System.out.println(count + "." + nextAssignment + "  failure");
        return false;
    }

}
