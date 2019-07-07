package com.nand2tetris.ch7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter out;
    private String fileName;

    public CodeWriter(FileWriter w) {
        out = new BufferedWriter(w);
    }

    public void setFileName(String fileName) {
        fileName = fileName;
    }

    public void writeArithmetic(String cmd) throws IOException {
        switch(cmd) {
            case "add":
                out.write("@0");
                out.newLine();
                out.write("A=M");
                out.newLine();
                out.write("D=M");
                out.newLine();
                out.write("@0");
                out.newLine();
                out.write("M=M-1");
                out.newLine();
                out.write("A=M");
                out.newLine();
                out.write("M=D+M");
                out.newLine();
                break;
        }
    }

    public void writePushPop(String cmd, String segment, String id) throws IOException {
        switch(cmd) {
            case "push":
                switch(segment) {
                    case "constant":
                        out.write("@" + id);
                        out.newLine();
                        out.write("D=A");
                        out.newLine();
                        out.write("@0");
                        out.newLine();
                        out.write("A=M");
                        out.newLine();
                        out.write("M=D");
                        out.newLine();
                        out.write("@0");
                        out.newLine();
                        out.write("M=M+1");
                        out.newLine();
                }
        }
    }

    public void close() throws IOException {
        out.close();
    }
}
