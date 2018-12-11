package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RAMTable {

    public static RAMTable ramTable = new RAMTable();
    public static TableView table;
    public static ObservableList<RAM> data;

    private RAMTable() { }

    public static ObservableList<RAM> getData() {
        return data;
    }

    public static RAMTable getInstance() {
        if (ramTable == null) {
            ramTable = new RAMTable();
        }
        return ramTable;
    }

    public static void setInitialRAMValues(Scene scene) {
        table = (TableView) scene.lookup("#ram_table");

        TableColumn address = new TableColumn("Address");
        TableColumn value = new TableColumn("Value");

        address.setMinWidth(30);
        address.setPrefWidth(75);
        address.setCellValueFactory(
                new PropertyValueFactory<RAM, Integer>("Address"));

        value.setMinWidth(30);
        value.setPrefWidth(75);
        value.setCellValueFactory(
                new PropertyValueFactory<RAM, Integer>("Value"));

        ArrayList<RAM> ram = new ArrayList<RAM>();

        for (int i = 0; i < 1000; i++) {
            ram.add(new RAM(i, 0));
        }

        data = FXCollections.observableArrayList(ram);

        table.setItems(data);
        table.getColumns().addAll(address, value);

    }
}
