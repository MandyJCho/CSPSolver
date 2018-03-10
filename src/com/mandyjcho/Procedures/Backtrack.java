package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

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

    private boolean isConsistent(Variable selection, int value, Assignment assignment) {
        HashMap<Variable, List<Integer>> variables = new HashMap<>(assignment.getFormattedSolution());

        for(Constraint constraint : constraints) {
            Variable other = constraint.getOther(selection);
            if (!variables.containsKey(other)) continue;
            List<Variable> constraintVars = List.of(selection, other);
            if (constraint.contains(constraintVars) && constraint.enforceOn(selection, value, variables).size() == 0)
                return false;
        }

        return true;
    }

    private boolean forwardCheck(Variable variable, int value, HashMap<Variable, List<Integer>> unassignedVars) {
        for(Constraint constraint : constraints) {
            Variable other = constraint.getOther(variable);
            if (other != null && unassignedVars.containsKey(other)) {
                List<Integer> domain = constraint.enforceOn(variable, value, unassignedVars);
                if (domain.size() == 0) return true;
                unassignedVars.put(other, domain);
            }
        }

        return false;
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
            if (isConsistent(variable, value, assignment)) {
                if (enforceFC && forwardCheck(variable, value, unassignedVars)) {
                    System.out.println(++count + "." + nextAssignment + "  failure");
                    continue;
                }
                if (solve(nextAssignment, new HashMap<>(unassignedVars))) return true;
            } else System.out.println(++count + "." + nextAssignment + "  failure");

            nextAssignment.remove(variable);
            if (count == 30) System.exit(0);
        }

        return false;
    }

}
