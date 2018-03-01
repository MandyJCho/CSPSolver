package com.mandyjcho.Components;

import java.util.HashMap;
import java.util.List;

public class Constraint {
    private Variable first;
    private Operator operator;
    private Variable second;

    public Constraint(List<String> input, HashMap<String, Variable> variables) {
        first = variables.get(input.get(0));
        operator = Operator.get(input.get(1));
        second = variables.get(input.get(2));
    }
}
