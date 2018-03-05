package com.mandyjcho.Procedures;

import com.mandyjcho.Components.Assignment;
import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.util.*;
import java.util.stream.Collectors;

public class Backtrack {
    private HashMap<String, Variable> variableMapping;
    private List<Constraint> constraints;
    private boolean enforceFC;

    public Backtrack(HashMap<String, Variable> variableMapping, List<Constraint> constraints, boolean enforceFC) {
        this.variableMapping = variableMapping;
        this.constraints = constraints;
        this.enforceFC = enforceFC;
    }

    //    • Whenever the solver needs to choose a value during the search process, apply the least
    //    constraining value heuristic. If more than one value remains after applying this heuristic,
    //    break ties by preferring smaller values.
    private List<Integer> orderDomain(Variable variable) {
        // call lists.sort and use comparator
        // cache the results to increase speed
        return null;
    }

    private boolean isConsistent() {
        return true;
    }

    private void forwardCheck() {}

    public void solve() {
        Assignment assignment = solve(new Assignment(), new ArrayList<>(variableMapping.values()));
        System.out.println(assignment);
    }

    private Assignment solve(Assignment assignment, List<Variable> variables) {
        if (variables.size() == 0) {
            assignment.setResult(true);
            return assignment;
        }

        Variable variable = Heuristics.getMostConstrainedVariable(variables, constraints);
        List<Integer> domain = orderDomain(variable);
        Assignment nextAssignment = new Assignment(assignment);
        for (int value : domain) {
            if (isConsistent()) {
                nextAssignment.assign(variable, value);
                if (enforceFC) forwardCheck();
                List<Variable> nextVariables = variables.stream()
                                                        .filter(v -> v != variable)
                                                        .collect(Collectors.toList());
                return nextAssignment;
//                Assignment result = solve(nextAssignment, nextVariables);
//                if (result.isGoal()) return result;
//                nextAssignment.remove(variable);
            }
        }
        return assignment;
    }

}
