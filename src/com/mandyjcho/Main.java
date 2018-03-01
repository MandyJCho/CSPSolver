package com.mandyjcho;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static HashMap<String, Variable> variables = new HashMap<>();
    static List<Constraint> constraints = new ArrayList<>();
    static boolean enforceFC;

    public static void main(String...args) {
	    // Extract arguments
        for (List<String> list : processFile(args[0]))
            variables.put(list.get(0), new Variable(list));

        // Extract constraint
        for(List<String> list : processFile(args[1]))
            constraints.add(new Constraint(list, variables));

        enforceFC = args[2].equals("fc");
    }

    public static List<List<String>> processFile(String path) {
        List<List<String>> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z0-9]|[<>=]+");
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
