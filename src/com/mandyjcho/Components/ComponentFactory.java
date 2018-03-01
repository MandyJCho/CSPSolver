package com.mandyjcho.Components;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Mandy Cho :) on 2/28/18.
 */

// Example of a factory using generics
public class ComponentFactory {

    public static <T> T generate(Class<T> Clazz, String[] input) {
        try {
            return Clazz.getConstructor(String[].class)
                    .newInstance(input);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }

    }

}
