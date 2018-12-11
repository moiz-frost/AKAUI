package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RegisterTable {

    public static RegisterTable registerTable = new RegisterTable();
    public static TableView table;
    public static ObservableList<Register> data;

    private RegisterTable() { }

    public static ObservableList<Register> getData() {
        return data;
    }

    public static RegisterTable getInstance() {
        if (registerTable == null) {
            registerTable = new RegisterTable();
        }
        return registerTable;
    }

    public static void setInitialRegisterValues(Scene scene) {
        table = (TableView) scene.lookup("#registerTable");

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

        data = FXCollections.observableArrayList(registers);


        table.setItems(data);
        table.getColumns().addAll(name, number, value);

    }



}