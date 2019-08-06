package ch7;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    private String currentCmd;
    private String[] args;
    private Scanner sc;

    private static final Set<String> arithmeticCommands = new HashSet<>(
            Arrays.asList("add", "sub", "neg", "eq",
                            "gt", "lt", "and", "or", "not"));

    public Parser(String file) throws IOException {
        sc = new Scanner(new File(file));
        currentCmd = sc.nextLine();
    }

    public boolean hasMoreCommands() {
        return sc.hasNextLine();
    }

    public void advance() {
        if (hasMoreCommands()) {
            currentCmd = sc.nextLine();
            args = currentCmd.split("\\s+");
        }
    }

    public String getCurrentCmd() {
        return currentCmd;
    }

    public String arg1() {
        if (args != null) {
            return args[0];
        } else {
            return null;
        }
    }

    public String arg2() {
        if (args != null && args.length > 1) {
            return args[1];
        } else {
            return null;
        }
    }

    public CommandType commandType() throws IllegalArgumentException {
        String arg1 = arg1();
        if (arithmeticCommands.contains(arg1)) {
            return CommandType.C_ARITHMETIC;
        } else {
            switch (arg1) {
                case "push":
                    return CommandType.C_PUSH;
                case "pop":
                    return CommandType.C_POP;
                case "label":
                    return CommandType.C_LABEL;
                case "goto":
                    return CommandType.C_GOTO;
                case "if-goto":
                    return CommandType.C_IF;
                case "function":
                    return CommandType.C_FUNCTION;
                case "return":
                    return CommandType.C_RETURN;
                case "call":
                    return CommandType.C_CALL;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public enum CommandType {
        C_ARITHMETIC, C_PUSH, C_POP, C_LABEL,
        C_GOTO, C_IF, C_FUNCTION, C_RETURN,
        C_CALL
    }

}
