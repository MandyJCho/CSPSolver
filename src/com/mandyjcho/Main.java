package com.mandyjcho;

import com.mandyjcho.Components.Constraint;
import com.mandyjcho.Components.Variable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static List<Variable> variables = new ArrayList<>();
    static List<Constraint> constraints = new ArrayList<>();

    public static void main(String...args) {
	    // Extract arguments
        processFile(args[0], variables);
        // Extract constraints
    }

    public static <T> void processFile(String path, List<T> list) {
        Pattern pattern = Pattern.compile("[A-Z0-9]|[<>=]+");
        try(Scanner scan = new Scanner(new File(path))) {
            while(scan.hasNext()) {
                Matcher match = pattern.matcher(scan.nextLine());
                match.find();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
