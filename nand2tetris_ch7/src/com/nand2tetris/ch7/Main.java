package com.nand2tetris.ch7;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("oops!");
            return;
        }
        System.out.println(args[0]);
    }
}
