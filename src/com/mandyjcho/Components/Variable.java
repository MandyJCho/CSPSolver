package com.mandyjcho.Components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Variable implements Comparable<Variable> {
    private String variable;
    private List<Integer> domain;
    private Integer assignment;

    public Variable(List<String> input) {
        variable = input.get(0);
        domain = input.stream()
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Variable(Variable v) {
        v.variable = variable;
        v.domain = new ArrayList<>(domain);
    }

    public Integer getAssignment() {
        return assignment;
    }

    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }

    public String getVariable() {
        return variable;
    }

    public List<Integer> getDomain() {
        return domain;
    }

    public void setDomain(List<Integer> domain) {
        this.domain = domain;
    }

    public boolean isEmpty() {
        return domain.isEmpty();
    }

    public int size() {
        return domain.size();
    }

    @Override
    public int hashCode() {
        return domain.hashCode();
    }

    @Override
    public String toString() { return variable; }

    @Override
    public int compareTo(Variable other) {
        return variable.compareTo(other.getVariable());
    }
    }

