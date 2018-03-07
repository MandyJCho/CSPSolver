package com.mandyjcho.Components;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Mandy Cho :) on 3/4/18.
 */
public class Assignment {
    private LinkedHashMap<Variable, Integer> solution;
    private boolean result;

    public boolean isGoal() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Assignment() {
        solution = new LinkedHashMap<>();
    }

    public Assignment(Assignment assignment) {
        this.solution = new LinkedHashMap<>(assignment.solution);
    }

    public boolean assign(Variable variable, int value) {
        if (!variable.getDomain().contains(value)) return false;

        solution.put(variable, value);

        return true;
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
        return solution.toString() + " " + result;
    }
}
