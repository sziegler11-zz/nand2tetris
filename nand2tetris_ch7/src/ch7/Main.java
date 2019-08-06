package ch7;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("You must supply an input file!");
            return;
        }
        String filePath = args[0];
        Parser parser = new Parser(filePath);
    }
}
