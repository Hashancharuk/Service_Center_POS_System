package controller;

import bo.BoFactory;
import bo.custom.EmployeeBo;
import com.jfoenix.controls.RecursiveTreeItem;
import dao.util.BoType;
import db.DBConnection;
import dto.CustomerDto;
import dto.EmployeeDto;
import dto.tm.EmployeeTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeRegisterFormController {
    public TextField txtEmpName;
    public TextField txtEmpEmail;
    public TextField txtEmpID;
    public TextField txtEmpContNum;
    public TextField txtEmpAddress;
    public TextField txtEmpPassword;
    public AnchorPane employeePane;
    public TableView tableEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn colContNum;
    public TableColumn colAddress;
    public TableColumn colOption;

    public Label labelEmpID;

    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
//        colPassword.setCellValueFactory(new PropertyValueFactory<>("empPassword"));
        colContNum.setCellValueFactory(new PropertyValueFactory<>("empContNum"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadEmployeeTable();
        generateEmployeeId();
        tableEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            setData((EmployeeTm) newValue);
        });
    }
    int num;
    private void generateEmployeeId() {
        try {
            EmployeeDto dto = employeeBo.lastEmployee();
            if (dto != null) {
                String id = dto.getId();
                num = Integer.parseInt(id.split("E")[1]) + 1;
                labelEmpID.setText(String.format("E%03d", num));
            } else {
                num = 1;
                labelEmpID.setText(String.format("E%03d", num));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(EmployeeTm newValue) {
        if (newValue != null) {
            txtEmpID.setEditable(false);
            txtEmpID.setText(newValue.getEmpId());
            txtEmpName.setText(newValue.getEmpName());
            txtEmpEmail.setText(newValue.getEmpEmail());
            txtEmpPassword.setText(newValue.getEmpPassword());
            txtEmpContNum.setText(newValue.getEmpContNum());
            txtEmpAddress.setText(newValue.getEmpAddress());
            labelEmpID.setText("");
        }
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> dtoList = employeeBo.allEmployees();
            for (EmployeeDto dto : dtoList) {
                Button btn = new Button("Delete");
                EmployeeTm employee = new EmployeeTm(
                        dto.getEmployeeId(),
                        dto.getEmployeeName(),
                        dto.getEmployeeEmail(),
                        dto.getEmployeePassword(),
                        dto.getEmployeeContNumber(),
                        dto.getEmployeeAddress(),
                        btn
                );
                btn.setOnAction(actionEvent -> {
                    try {
                        deleteEmployee(employee.getEmpId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                tmList.add(employee);
            }
            tableEmployee.setItems(tmList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws  ClassNotFoundException, SQLException{
        try {
            boolean isSaved = employeeBo.saveEmployee(
                    new EmployeeDto(
                            txtEmpID.getText(),
                            txtEmpName.getText(),
                            txtEmpEmail.getText(),
                            txtEmpPassword.getText(),
                            txtEmpContNum.getText(),
                            txtEmpAddress.getText()
                    ));
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Employee Saved!").show();
                labelEmpID.setText(String.format("E%03d",++num));
            }else {
                new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) employeePane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        boolean isDeleted = employeeBo.deleteEmployee(empId);
        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted!").show();
            loadEmployeeTable();
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = employeeBo.updateEmployee(new EmployeeDto(
                    txtEmpID.getText(),
                    txtEmpName.getText(),
                    txtEmpEmail.getText(),
                    txtEmpPassword.getText(),
                    txtEmpContNum.getText(),
                    txtEmpAddress.getText()
            ));

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Employee Updated!").show();
                loadEmployeeTable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            new Alert(Alert.AlertType.ERROR, "Error during update!").show();
        }
    }


    private void clearFields() {
        tableEmployee.refresh();
        txtEmpID.clear();
        txtEmpName.clear();
        txtEmpEmail.clear();
        txtEmpPassword.clear();
        txtEmpContNum.clear();
        txtEmpAddress.clear();
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
    }

    public void reloadButtonOnAction(ActionEvent actionEvent) {
        loadEmployeeTable();
        tableEmployee.refresh();
        clearFields();
    }

    public void reportButtonOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/empReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

