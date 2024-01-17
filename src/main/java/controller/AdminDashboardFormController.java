package controller;

import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.io.IOException;
import java.sql.SQLOutput;

public class AdminDashboardFormController {

    public Label lblTime;
    public Label lblDate;
    public Label lblTime1;
    @FXML
    private AnchorPane pane;

    public void initialize(){
        loadDateAndTime();
    }
Date date;
    private void loadDateAndTime() {
        date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void addItemBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemCategoryForm.fxml"))));
        stage.setTitle("Add Item");
        stage.show();
    }


    public void customerReportOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"))));
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.show();
    }


    public void addEmployeeBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/EmployeeRegisterForm.fxml"))));
        stage.setTitle("Add Employee");
        stage.setResizable(false);
        stage.show();
    }


    public void addCustBtnOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = (Stage) pane.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"))));
//        stage.setTitle("Add Customer");
//        stage.show();
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
        stage.setTitle("Place Order");
        stage.setResizable(false);
        stage.show();
    }

    public void logOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"))));
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }

    public void reportsBtnOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reports");
        alert.setHeaderText("Please select your report type");
        alert.setContentText("Select which report type you want");

        ButtonType salesReportButton = new ButtonType("Sales Report");
        ButtonType customerReportButton = new ButtonType("Customer Report");
        ButtonType orderReportButton = new ButtonType("Order Report");
        ButtonType employeeReportButton = new ButtonType("Employee Report");

        alert.getButtonTypes().setAll(salesReportButton, customerReportButton,orderReportButton,employeeReportButton, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == salesReportButton) {
            System.out.println("Report01");
        } else if (result.isPresent() && result.get() == customerReportButton) {
            System.out.println("Report02");
        } else if (result.isPresent() && result.get() == orderReportButton) {
            System.out.println("Report03");
        } else if (result.isPresent() && result.get() == employeeReportButton) {
            JasperDesign design = null;
            try {
                design = JRXmlLoader.load("src/main/resources/reports/empReport.jrxml");
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } else {
            System.out.println("Cancel or close");
        }

    }
}
