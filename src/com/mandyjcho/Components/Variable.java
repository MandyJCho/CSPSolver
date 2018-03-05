package com.mandyjcho.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Variable implements Comparable<Variable> {
    private String name;
    private List<Integer> domain;

    public Variable(List<String> input) {
        name = input.get(0);
        domain = input.stream()
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Variable(Variable v) {
        v.name = name;
        v.domain = new ArrayList<>(domain);
    }

    public String getName() {
        return name;
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

    public boolean equalsType(Variable variable) {
        return variable.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return domain.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;

        if (obj == this) return true;

        Variable objVariable = (Variable) obj;

        return objVariable.getName().equals(this.name) && objVariable.getDomain().equals(domain);
    }

    @Override
    public String toString() { return name; }

    @Override
    public int compareTo(Variable other) {
        return name.compareTo(other.getName());
    }
    }

