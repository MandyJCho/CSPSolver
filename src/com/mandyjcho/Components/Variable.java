package com.mandyjcho.Components;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Variable {
    private String variable;
    private List<Integer> domain;

    public Variable(List<String> input) {
        variable = input.get(0);
        domain = input.stream()
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public String getVariable() {
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
        return domain.hashCode();
    }
}
