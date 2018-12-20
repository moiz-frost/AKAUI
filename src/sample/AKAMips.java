package sample;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.util.*;

// http://www.dsi.unive.it/~gasparetto/materials/MIPS_Instruction_Set.pdf

public class AKAMips {

    int[] memory = new int[1000];
    int registers[] = new int[35];
    int mp = 0;
    LinkedMap<String, Integer> regMap;
    Parser parser;
    Scanner input;
    File prog;

    AKAMips() {
        input = new Scanner(System.in);
        regMap = new LinkedMap<String, Integer>();
        regMap.put("$zero", 0);
        regMap.put("$at", 1);
        regMap.put("$v0", 2);
        regMap.put("$v1", 3);
        for (int i = 0; i < 4; i++) {
            regMap.put("$a" + (i), i + 4);
        }
        for (int i = 0; i < 8; i++) {
            regMap.put("$t" + (i), i + 8);
        }
        for (int i = 0; i < 8; i++) {
            regMap.put("$s" + (i), i + 16);
        }
        regMap.put("$a8", 24);
        regMap.put("$a9", 25);
        regMap.put("$k0", 26);
        regMap.put("$k1", 27);
        regMap.put("$gp", 28);
        regMap.put("$sp", 29);
        regMap.put("$fp", 30);
        regMap.put("$ra", 31);
        regMap.put("$pc", 32);
        regMap.put("$hi", 33);
        regMap.put("$lo", 34);
        registers[29] = memory.length;
        int rtx = 2;

    }
    void loadFile(File file){
        prog = file;
        parser = new Parser(this);
    }
    void exec() {
        for (registers[32] = 0; registers[32] < parser.code.size(); registers[32]++) {
            parser.parseLine(registers[32]);
        }
    }

    void print_int() {
        System.out.print(registers[4]);
    }

    void print_string() {
        String s = "";
        int i = registers[4];
        for (boolean k = false;; k = !k) {
            if (!k) {
                char c = (char) (memory[i] >> 16);
                if (c == '\u0000') {
                    break;
                }
                s += c;
            } else {
                char c = (char) (memory[i++] & 0xFFFF);
                if (c == '\u0000') {
                    break;
                }
                s += c;
            }
        }
        System.out.print(s);
    }

    void read_int() {
        registers[2] = input.nextInt();
//        TextInputDialog dialog = new TextInputDialog("walter");
//        dialog.setTitle("Text Input Dialog");
//        dialog.setHeaderText("Look, a Text Input Dialog");
//        dialog.setContentText("Please enter your name:");
//        dialog.show();
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()){
//            System.out.println("Your name: " + result.get());
//        }
    }

    void read_string() {
        String s = input.next(".*\\n\\n");
        int start = mp;
        boolean k = false;
        for (int j = 0; j < s.length(); j++, k = !k) {
            if (!k) {
                memory[mp] = s.charAt(j) << 16;
            } else {
                memory[mp++] += s.charAt(j);
            }
        }
        if (!k) {
            memory[mp++] = '\u0000';
        } else {
            memory[mp++] += '\u0000';
        }
        registers[2]=start;
        registers[3]=s.length();

    }

    public void run() {
//        File file;
//        file = new File("temp.asm");
//        AKAMips asm = new AKAMips();
//        asm.loadFile(file);
//        asm.exec();
//        file = new File("program.asm");
//        asm = new AKAMips();
//        asm.loadFile(file);
//        asm.exec();
//        file = new File("IO.asm");
//        asm = new AKAMips();
//        asm.loadFile(file);
//        asm.exec();
//        file = new File("fibonacci.asm");
//        asm = new AKAMips();
//        asm.loadFile(file);
//        asm.exec();
//        file = new File("loop.asm");
//        asm = new AKAMips();
//        asm.loadFile(file);
//        asm.exec();
    }

}
