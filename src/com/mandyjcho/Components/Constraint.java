package com.mandyjcho.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Constraint.
 * Denotes relationships between variables
 */
public class Constraint {
    private Variable first;
    private Operator operator;
    private Variable second;

    /**
     * Instantiates a new Constraint.
     *
     * @param input     the input
     * @param variables the variables
     */
    public Constraint(List<String> input, HashMap<String, Variable> variables) {
        first = variables.get(input.get(0));
        operator = Operator.get(input.get(1));
        second = variables.get(input.get(2));
    }

    /**
     * Contains variable boolean.
     *
     * @param variable the variable
     * @return boolean representation of whether or not the constraint contains the variable
     */
    public boolean containsVariable(Variable variable) {
        return variable == first || variable == second;
    }

    public Operator getOperator() {
        return operator;
    }

    /**
     * Gets variables in the constraint that have yet to be assigned
     *
     * @return a list of the unconstrained variables in the constraint
     */
    public List<Variable> getVariables() {
        return Arrays.asList(new Variable[] {first, second});
    }

    public boolean isApplicable() {
        return first.getAssignment() == null && second.getAssignment() == null;
    }

    /**
     * Enforce a constraint on a variable's domain
     *
     * @param enforcee the variable whose domain is going to be reduced
     * @param value    the value we're filtering by
     * @return the boolean
     */
    public List<Integer> enforceOn(Variable enforcee, int value) {
        // Validate parameters before using
        Variable enforcer = enforcee == first ? second : first;
        if (!containsVariable(enforcee) || !enforcer.getDomain().contains(value)) return new ArrayList<>();

        return enforcee.getDomain().stream().filter((enforceeValue) -> {
            switch(operator) {
                case LESS:
                    return value < enforceeValue;
                case GREATER:
                    return value > enforceeValue;
                case EQUAL:
                    return value == enforceeValue;
                default:
                    return value != enforceeValue;
            }
        }).collect(Collectors.toList());
    }

}
