package controller;

import bo.BoFactory;
import bo.custom.ItemCategoryBo;
import com.jfoenix.controls.JFXComboBox;
import dao.util.BoType;
import dto.ItemCategoryDto;
import dto.tm.ItemCategoryTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddItemCategoryFormController implements Initializable {
    public JFXComboBox cmbCategory;
    public TextField txtItemName;
    public TextField txtItemCode;
    public TableView tableItem;
    public TableColumn colId;
    public TableColumn colCategory;
    public TableColumn colName;
    public TableColumn colOption;

    public ItemCategoryBo itemCategoryBo = BoFactory.getInstance().getBo(BoType.ITEMCATEGORY);
    public AnchorPane addItemPane;

    private void setData(ItemCategoryTm newValue) {
        if (newValue != null) {
            cmbCategory.getSelectionModel().select(newValue.getCategory());
            txtItemCode.setText(newValue.getId());
            txtItemName.setText(newValue.getName());
        }
    }

    private void loadItemCategoryTable() {
        ObservableList<ItemCategoryTm> tmList = FXCollections.observableArrayList();
        try {
            List<ItemCategoryDto> dtoList = itemCategoryBo.allItems();

            for (ItemCategoryDto dto : dtoList) {
                Button btn = new Button("Delete");
                ItemCategoryTm it = new ItemCategoryTm(
                        dto.getId(),
                        dto.getCategory(),
                        dto.getName(),
                        btn
                );
                btn.setOnAction(actionEvent -> deleteItem(it.getId()));
                tmList.add(it);
            }
            tableItem.setItems(tmList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String id) {
        try {
            boolean isDeleted = itemCategoryBo.deleteItem(id);
            if (isDeleted) {
                loadItemCategoryTable();
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete item!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void savebtnOnAction(ActionEvent actionEvent) {
        try {
            boolean isSaved = itemCategoryBo.saveItem(
                    new ItemCategoryDto(
                            txtItemCode.getText(),
                            txtItemName.getText(),
                            cmbCategory.getSelectionModel().getSelectedItem().toString()
                    ));
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemCategoryTable();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = itemCategoryBo.updateItem(new ItemCategoryDto(
                    txtItemCode.getText(),
                    txtItemName.getText(),
                    cmbCategory.getSelectionModel().getSelectedItem().toString()
            ));
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item Updated!").show();
                loadItemCategoryTable();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.getItems().addAll("Electrical", "Electronic");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadItemCategoryTable();

        tableItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if (null!=newValue){
                setData((ItemCategoryTm) newValue);
            }
        } ));
    }

    public void reloadbtnOnAction(ActionEvent actionEvent) {
        loadItemCategoryTable();
        tableItem.refresh();
        clearFields();
    }

    private void clearFields() {
        tableItem.refresh();
        txtItemName.clear();
        txtItemCode.clear();
    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) addItemPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
