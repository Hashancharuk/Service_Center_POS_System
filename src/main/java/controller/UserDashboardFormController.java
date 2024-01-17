package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardFormController {

    public AnchorPane userDashBoardPane;

    public void changePasswordBtnOnAction(ActionEvent actionEvent) {
    }

    public void addItemBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userDashBoardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemCategoryForm.fxml"))));
        stage.setTitle("Add Item Category");
        stage.setResizable(false);
        stage.show();
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userDashBoardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
        stage.setTitle("Place Order");
        stage.setResizable(false);
        stage.show();
    }
    public void logOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userDashBoardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"))));
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.show();
    }


    public void addCustomerBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userDashBoardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"))));
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.show();
    }

    public void orderDetailBtnOnAction(ActionEvent actionEvent) {
    }
}
