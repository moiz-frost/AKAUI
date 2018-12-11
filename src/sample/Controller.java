package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextArea code;

    @FXML
    public void chooseSingleFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open asm file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Asm Files", "*.asm"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Scanner input = null;
            try {
                input = new Scanner(selectedFile);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
                return;
            }
            while (input.hasNext()) {
                String line = input.nextLine().trim();
                code.appendText(line + "\n");
            }
        }
    }

    @FXML
    public void executeCode(ActionEvent event) {
        code.getParagraphs().forEach((line) -> {
            System.out.println(line);
        });
        /* Do your parsing/exec logic here*/
    }
}
