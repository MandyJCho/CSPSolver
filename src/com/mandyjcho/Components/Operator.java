package com.mandyjcho.Components;

import java.util.HashMap;

public enum Operator {
    GREATER(">"),
    LESS("<"),
    NOTEQUAL("!"),
    EQUAL("=");

    private static HashMap<String, Operator> map = new HashMap<>();

    static {
        for(Operator operator : Operator.values())
            map.put(operator.getSymbol(), operator);
    }

    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operator get(String symbol) {
        return map.get(symbol);
    }
}
