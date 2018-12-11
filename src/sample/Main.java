package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private void setInitialRegisterValues(Scene scene) {
        TableView registerTable = (TableView) scene.lookup("#registerTable");

        TableColumn name = new TableColumn("Name");
        TableColumn value = new TableColumn("Value");
        TableColumn number = new TableColumn("Number");

        name.setMinWidth(10);
        name.setPrefWidth(75);
        name.setCellValueFactory(
                new PropertyValueFactory<Register, String>("Name"));

        number.setMinWidth(10);
        number.setPrefWidth(75);
        number.setCellValueFactory(
                new PropertyValueFactory<Register, String>("Number"));

        value.setMinWidth(10);
        value.setPrefWidth(75);
        value.setCellValueFactory(
                new PropertyValueFactory<Register, String>("Value"));

        ArrayList<Register> registers = new ArrayList<Register>();

        registers.add(new Register("$zero", 0, 0));
        registers.add(new Register("$at", 1, 0));
        registers.add(new Register("$v0", 2, 0));
        registers.add(new Register("$v1", 3, 0));
        for (int i = 0; i < 4; i++) {
            registers.add(new Register("$a" + (i), i + 4, 0));
        }
        for (int i = 0; i < 8; i++) {
            registers.add(new Register("$t" + (i), i + 8, 0));
        }
        for (int i = 0; i < 8; i++) {
            registers.add(new Register("$s" + (i), i + 16, 0));
        }
        registers.add(new Register("$a8", 24, 0));
        registers.add(new Register("$a9", 25, 0));
        registers.add(new Register("$k0", 26, 0));
        registers.add(new Register("$k1", 27, 0));
        registers.add(new Register("$gp", 28, 0));
        registers.add(new Register("$sp", 29, 0));
        registers.add(new Register("$fp", 30, 0));
        registers.add(new Register("$ra", 31, 0));
        registers.add(new Register("$pc", 32, 0));
        registers.add(new Register("$hi", 33, 0));
        registers.add(new Register("$lo", 34, 0));

        ObservableList<Register> data = FXCollections.observableArrayList(registers);

        registerTable.setItems(data);
        registerTable.getColumns().addAll(name, number, value);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MIPS ASM Simulator");
        Scene scene = new Scene(root);
        this.setInitialRegisterValues(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
