package com.mandyjcho.Components;

import java.util.*;

/**
 * Created by Mandy Cho :) on 3/4/18.
 */
public class Assignment {
    private LinkedHashMap<Variable, Integer> solution;

    public Assignment() {
        solution = new LinkedHashMap<>();
    }

    public Assignment(Assignment assignment) {
        this.solution = new LinkedHashMap<>(assignment.solution);
    }

    public void assign(Variable variable, int value) {
        if (!variable.getDomain().contains(value)) return;

        solution.put(variable, value);
    }

    public int get(Variable variable) {
        return solution.getOrDefault(variable, null);
    }

    public void remove(Variable variable) {
        solution.remove(variable);
    }

    public boolean isConstraintApplicable(Constraint constraint) {
        for (Variable variable : constraint.getVariables())
            if (solution.containsKey(variable)) return false;

        return true;
    }

    public HashMap<Variable, List<Integer>> getFormattedSolution() {
        HashMap<Variable, List<Integer>> formattedSolution = new HashMap<>();
        for (Variable variable : solution.keySet())
            formattedSolution.put(variable, List.of(solution.get(variable)));

        return formattedSolution;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Assignment)) return false;

        if (obj == this) return true;

        return solution.equals(((Assignment) obj).solution);
    }

    @Override
    public int hashCode() {
        return solution.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Variable variable : solution.keySet())
            sb.append(" " + variable + "=" + solution.get(variable) + ",");
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
