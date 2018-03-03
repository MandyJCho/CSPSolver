package com.mandyjcho.Components;

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


    /**
     * Enforce a constraint on a variable's domain
     * ** Order matters in the case of we want to enforce first onto second and vice versa
     * @param enforcee the variable whose domain is going to be reduced
     * @param value    the value we're filtering by
     * @return the boolean
     */
    public Boolean enforceValueOn(Variable enforcee, int value) {
        // Validate enforcement
        Variable enforcer = enforcee == first ? second : first;
        if (!containsVariable(enforcee)) return null;
        if (!enforcer.getDomain().contains(value)) return null;

        List<Integer> domain = enforcee.getDomain().stream().filter((enforceeValue) -> {
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

        enforcee.setDomain(domain);

        return enforcee.isEmpty();
    }
}
