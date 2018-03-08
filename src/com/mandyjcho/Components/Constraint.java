package com.mandyjcho.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Constraint {
    private final Variable first;
    private final Operator operator;
    private final Variable second;

    public Constraint(List<String> input, HashMap<String, Variable> variables) {
        first = variables.get(input.get(0));
        operator = Operator.get(input.get(1));
        second = variables.get(input.get(2));
    }

    public boolean contains(Variable variable) {
        return variable == first || variable == second;
    }

    public List<Variable> getVariables() {
        return Arrays.asList(first, second);
    }

    public Variable getOther(Variable variable) {
        if (variable == first) return second;
        else if (variable == second) return first;
        return null;
    }

    public List<Integer> enforceOn(Variable enforcer, int value, HashMap<Variable, List<Integer>> variables) {
        List<Integer> enforceeDomain = enforcer == first ? variables.get(second) : variables.get(first);

        if (enforceeDomain == null || !contains(enforcer) )
            return new ArrayList<>();

        return enforceeDomain.stream().filter((enforceeValue) -> {
            switch(operator) {
                case LESS:
                    return enforcer == first ? (value < enforceeValue) : (value > enforceeValue);
                case GREATER:
                    return enforcer == first ? (value > enforceeValue) : (value < enforceeValue);
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
