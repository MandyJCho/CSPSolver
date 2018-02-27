package com.mandyjcho.Components;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Variable {
    private char variable;
    private List<Integer> domain;

    public Variable(String[] input) {
        variable = input[0].charAt(0);
        domain = Stream.of(input)
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public char getVariable() {
        return variable;
    }

    public List<Integer> getDomain() {
        return domain;
    }

    public boolean forwardCheck(List<Constraint> constraints) {
        return domain.isEmpty();
    }

    @Override
    public int hashCode() {
        return Character.hashCode(variable);
    }
}
