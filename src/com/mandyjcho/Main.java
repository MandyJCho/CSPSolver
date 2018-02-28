package com.mandyjcho;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<Variable> variables = new ArrayList<>();
    List<Constraint> constraints = new ArrayList<>();
    public static void main(String...args) {
	    // Extract arguments

        // Extract constraints
    }

    public static <T> void processFile(String path, List<T> list) throws IOException {
        try(Scanner scan = new Scanner(new File(path))) {
            while(scan.hasNext()) {
                list.add(new T(scan.nextLine().split("(\\s|:)")));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
