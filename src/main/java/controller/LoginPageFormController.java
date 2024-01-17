package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginPageFormController {
    public JFXPasswordField password;
    public JFXTextField username;
    public AnchorPane pane;

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        String userName = username.getText();
        String passWord = password.getText();
        if (userName.equals("admin") && passWord.equals("admin")){
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoardForm.fxml"))));
            stage.setTitle("Admin DashBoard");
            stage.show();
        } else if (userName.equals("user") && passWord.equals("user")) {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashBoardForm.fxml"))));
            stage.setTitle("Employee DashBoard");
            stage.show();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Incorrect Username or Password");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK);
        }
    }
}
