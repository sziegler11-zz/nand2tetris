package ch7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter out;
    private String fileName;
    private int labelCounter = -1;
    private String trueLabel = "";
    private String endLabel = "";

    public CodeWriter(FileWriter w) {
        out = new BufferedWriter(w);
    }

    public void setFileName(String fileName) {
        fileName = fileName;
    }

    public void writeArithmetic(String cmd) throws IOException {
        writeWithNewLine("// " + cmd);
        out.newLine();
        switch(cmd) {
            case "add":
                writeWithNewLine("@0");
                writeWithNewLine("A=M-1");
                writeWithNewLine("D=M");
                writeWithNewLine("@0");
                writeWithNewLine("M=M-1");
                writeWithNewLine("A=M-1");
                writeWithNewLine("M=D+M");
                break;
            case "sub":
                writeWithNewLine("@0");
                writeWithNewLine("A=M-1");
                writeWithNewLine("D=M");
                writeWithNewLine("@0");
                writeWithNewLine("M=M-1");
                writeWithNewLine("A=M-1");
                writeWithNewLine("M=M-D");
                break;
            case "neg":
                writeWithNewLine("@0");
                writeWithNewLine("A=M-1");
                writeWithNewLine("M=-M");
                break;
            case "eq":
                writeLogicalOp("JMP");
            case "gt":
                writeLogicalOp("JGT");
            case "lt":
                writeLogicalOp("JLT");
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

    private void writeLogicalOp(String jmpCmd) throws IOException {
        trueLabel = nextLabel();
        endLabel = nextLabel();
        writeWithNewLine("@0");
        writeWithNewLine("M=M-1");
        writeWithNewLine("A=M");
        writeWithNewLine("D=M");
        writeWithNewLine("A=A-1");
        writeWithNewLine("D=M-D");
        writeWithNewLine("@label" + trueLabel);
        // check conditional
        writeWithNewLine("D;"+ jmpCmd);
        // neq logic then go to end
        writeWithNewLine("@0");
        writeWithNewLine("A=M-1");
        writeWithNewLine("M=0");
        writeWithNewLine("@label" + endLabel);
        writeWithNewLine("0;JMP");
        writeWithNewLine("(label" + trueLabel + ")");
        writeWithNewLine("@0");
        writeWithNewLine("A=M-1");
        writeWithNewLine("M=-1");
        writeWithNewLine("(label" + endLabel + ")");
    }

    private String nextLabel(){
        labelCounter += 1;
        return String.valueOf(labelCounter);
    }

    private void writeWithNewLine(String s) throws IOException {
        out.write(s);
        out.newLine();
    }

    private void advanceStackPointer() throws IOException {
        out.write("@0");
        out.write("M=M+1");
        out.newLine();
    }
}
