package com.mandyjcho;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Main.
 */
public class Main {
    static private HashMap<String, Variable> variables = new HashMap<>();
    static private List<Constraint> constraints = new ArrayList<>();
    static private boolean enforceFC;

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String...args) {
	    // Extract arguments
        for (List<String> list : processFile(args[0]))
            variables.put(list.get(0), new Variable(list));

        // Extract constraint
        for(List<String> list : processFile(args[1]))
            constraints.add(new Constraint(list, variables));

        enforceFC = args[2].equals("fc");

        solveWithLimit();
    }

    /**
     * Process each line in the file for the variables and constraints
     *
     * @param path path to resource file
     * @return a list of string lists. Each string lists represents a parsed line from the input file
     */
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

    /**
     * Attempts to satisfy the constraint within 30 moves
     */
    public static void solveWithLimit() {


    }

//    • Whenever the solver needs to choose a variable during the search process, apply the most
//    constrained variable heuristic, breaking ties using the most constraining variable heuristic.
//    If more than one variable remains after applying these heuristics, break ties alphabetically.

//    • Whenever the solver needs to choose a value during the search process, apply the least
//    constraining value heuristic. If more than one value remains after applying this heuristic,
//    break ties by preferring smaller values.

        // check domains
        // checks if any of the domains are 0-ed out

    }
