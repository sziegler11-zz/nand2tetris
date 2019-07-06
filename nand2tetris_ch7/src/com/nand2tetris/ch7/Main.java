package com.nand2tetris.ch7;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser("/Users/sziegler/testParser.txt");
        while (parser.hasMoreCommands()) {
            parser.advance();
            System.out.println(parser.getCurrentCmd());
        }
    }
}
