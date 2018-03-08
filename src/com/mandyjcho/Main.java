package com.mandyjcho;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;
import com.mandyjcho.Procedures.Backtrack;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Main.
 */
public class Main {
    public static void main(String...args) {
        try {
            HashMap<String, Variable> variables = new HashMap<>();
            for (List<String> list : processFile(args[0]))
                variables.put(list.get(0), new Variable(list));

            List<Constraint> constraints = new ArrayList<>();
            for (List<String> list : processFile(args[1]))
                constraints.add(new Constraint(list, variables));

            boolean enforceFC = args[2].equals("fc");

            new Backtrack(variables, constraints, enforceFC).solve();

        } catch(NullPointerException e) {
            System.out.println("Expected 3 arguments but got " + args.length);
        }
    }

    public static List<List<String>> processFile(String path) {
        List<List<String>> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z0-9]+|[<>=]+");
        try(Scanner scan = new Scanner(new File(path))) {
            while(scan.hasNext()) {
                ArrayList<String> parsedInput = new ArrayList<>();
                Matcher match = pattern.matcher(scan.nextLine());
                while (match.find())
                    parsedInput.add(match.group());
                lines.add(parsedInput);
            }

            return lines;

        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
