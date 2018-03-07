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
    private final Variable first;
    private final Operator operator;
    private final Variable second;

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
        return Arrays.asList(first, second);
    }

    /**
     * Enforce a constraint on a variable's domain
     *
     * @param enforcer the variable we want to rule by
     * @param value    the value we're filtering by
     * @return the boolean
     */
    public List<Integer> enforceOn(Variable enforcer, int value) {
        // Validate parameters before using
        Variable enforcee = enforcer == first ? second : first;
        if (!containsVariable(enforcer) || !enforcee.getDomain().contains(value)) return new ArrayList<>();

        if (enforcer == first)
            return enforcee.getDomain().stream().filter((enforceeValue) -> {
            switch(operator) {
                case LESS:
                    System.out.println(value + " " + enforceeValue);
                    return value < enforceeValue;
                case GREATER:
                    return value > enforceeValue;
                case EQUAL:
                    return value == enforceeValue;
                default:
                    return value != enforceeValue;
            }
        }).collect(Collectors.toList());

        return enforcee.getDomain().stream().filter((enforceeValue) -> {
            switch(operator) {
                case LESS:
                    return value > enforceeValue;
                case GREATER:
                    return value < enforceeValue;
                case EQUAL:
                    return value == enforceeValue;
                default:
                    return value != enforceeValue;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return first + " " + operator.getSymbol() + " " + second;
    }
}
