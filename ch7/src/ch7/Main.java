package ch7;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("You must supply an input file!");
            return;
        }
        String filePath = args[0];
        String[] pathComps = filePath.split("/");
        String outputFileName = pathComps[pathComps.length - 1].split("\\.")[0] + ".asm";
        Parser parser = new Parser(filePath);
        CodeWriter cw = new CodeWriter(new FileWriter(outputFileName));

        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.commandType() == Parser.CommandType.C_ARITHMETIC) {
                cw.writeArithmetic(parser.arg1());
            } else if (parser.commandType() == Parser.CommandType.C_PUSH) {
                cw.writePushPop(parser.arg1(), parser.arg2(), parser.arg3());
            }
        }

        cw.close();
    }
}
