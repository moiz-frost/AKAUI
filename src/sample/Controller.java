package sample;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;

public class Controller implements Initializable{

    @FXML
    private AnchorPane anchor;

    @FXML
    private MenuItem open;
    
    @FXML
    private MenuItem save;
    
    @FXML
    private MenuItem assemble;
    
    @FXML
    private MenuItem run_menu_item;

    @FXML
    private MenuItem step_forward;

    @FXML
    private TextArea code;

    @FXML
    private TextArea console;

    AKAMips asm = new AKAMips();

    File selectedFile;

    KeyCombination savekp = new KeyCharacterCombination("S", KeyCombination.CONTROL_DOWN);
    KeyCombination openkp = new KeyCharacterCombination("O", KeyCombination.CONTROL_DOWN);
    KeyCombination assemblekp = new KeyCodeCombination(KeyCode.F5);
    KeyCombination runkp = new KeyCodeCombination(KeyCode.F6);
    KeyCombination stepkp = new KeyCodeCombination(KeyCode.F8);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        open.setAccelerator(openkp);
        save.setAccelerator(savekp);
        assemble.setAccelerator(assemblekp);
        run_menu_item.setAccelerator(runkp);
        step_forward.setAccelerator(stepkp);
    }




    @FXML
    public void chooseSingleFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open asm file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Asm Files", "*.asm"));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Scanner input = null;
            try {
                input = new Scanner(selectedFile);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
                return;
            }
            code.setText("");
            while (input.hasNext()) {
                String line = input.nextLine().trim();
                code.appendText(line + "\n");
            }
            assemble.setDisable(false);
            code.setDisable(false);
        }
    }

    @FXML
    public void saveFile(ActionEvent event) {
        if (selectedFile != null) {
            try {
                File file = selectedFile;
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("");
                code.getParagraphs().forEach((line) -> {
                    try {
                        fileWriter.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Saved to " + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file opened");
        }
    }

    @FXML
    public void executeCode(ActionEvent event) {
        asm.exec();
        for (int i = 0; i < asm.registers.length; i++) {
            RegisterTable.data.get(i).setValue(asm.registers[i]);
        }
        for (int i = 0; i < asm.memory.length; i++) {
            RAMTable.data.get(i).setValue(asm.memory[i]);
        }
        RAMTable.table.refresh();
        RegisterTable.table.refresh();
//        RegisterTable.getData().forEach((val) -> {
//            System.out.println();
//        });
//        System.out.println(RegisterTable.getData().get(0).getName());
//        RegisterTable.data.get(0).setNumber(99);
//        RegisterTable.table.refresh();


        /* Do your parsing/exec logic here*/
    }

    @FXML
    public void stepForward(ActionEvent event) {
        if (asm.registers[32] < asm.parser.code.size()) {
            asm.parser.parseLine(asm.registers[32]++);
            for (int i = 0; i < asm.registers.length; i++) {
                RegisterTable.data.get(i).setValue(asm.registers[i]);
            }
            for (int i = 0; i < asm.memory.length; i++) {
                RAMTable.data.get(i).setValue(asm.memory[i]);
            }
            RAMTable.table.refresh();
            RegisterTable.table.refresh();
        }
    }

    @FXML
    public void assemble(ActionEvent event) {
        asm.loadFile(selectedFile);
        for (int i = 0; i < asm.memory.length; i++) {
            RAMTable.data.get(i).setValue(asm.memory[i]);
        }
        asm.registers[32] = 0;
        RegisterTable.data.get(32).setValue(0);
        RegisterTable.table.refresh();
        RAMTable.table.refresh();
        step_forward.setDisable(false);
        run_menu_item.setDisable(false);
        System.err.println("Assembled");
    }

    
}
