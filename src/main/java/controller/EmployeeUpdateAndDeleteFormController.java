//package controller;
//
//import bo.BoFactory;
//import bo.custom.EmployeeBo;
//import com.jfoenix.controls.JFXTreeTableView;
//import com.jfoenix.controls.RecursiveTreeItem;
//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//import dao.util.BoType;
//import dto.EmployeeDto;
//import dto.tm.EmployeeTm;
//import javafx.beans.Observable;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.TreeItemPropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.fxml.FXML;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.function.Predicate;
//
//public class EmployeeUpdateAndDeleteFormController {
//    public TextField txtSearchEmpID;
//    public TextField txtEmpName;
//    public TextField txtEmpEmail;
//    public TextField txtEmpContNum;
//    public TextField txtEmpAddress;
//    public AnchorPane updatePane;
//    public TreeTableColumn colEmpID;
//    public TreeTableColumn colEmpName;
//    public TreeTableColumn colEmpEmail;
//    public TreeTableColumn colEmpNumber;
//    public TreeTableColumn colEmpAddress;
//    public TreeTableColumn colOption;
//    public TextField txtEmpPassword;
//    public TreeTableColumn colPassword;
//    public JFXTreeTableView tblEmployeeDetails;
//
//
//    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);
//
//    public void initialize(){
//        colEmpID.setCellFactory(new TreeItemPropertyValueFactory<>("empId"));
//        colEmpName.setCellFactory(new TreeItemPropertyValueFactory<>("empName"));
//        colEmpEmail.setCellFactory(new TreeItemPropertyValueFactory<>("empEmail"));
//        colEmpNumber.setCellFactory(new TreeItemPropertyValueFactory<>("empContNum"));
//        colEmpAddress.setCellFactory(new TreeItemPropertyValueFactory<>("empAddress"));
//        colOption.setCellFactory(new TreeItemPropertyValueFactory<>("btn"));
//        loadEmployeeTable();
//        tblEmployeeDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changed(observable, (TreeItem<EmployeeTm>) oldValue, (TreeItem<EmployeeTm>) newValue));
//
//        txtSearchEmpID.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
//                tblEmployeeDetails.setPredicate(new Predicate<TreeItem<EmployeeTm>>() {
//                    @Override
//                    public boolean test(TreeItem<EmployeeTm> treeItem) {
//                        return treeItem.getValue().getEmpId().contains(newValue);
//                    }
//                });
//            }
//        });
//    }
//
//    private void changed(Observable observableValue,TreeItem<EmployeeTm> oldValue,TreeItem<EmployeeTm>newValue) {
//        if (newValue != null && newValue.getValue() instanceof EmployeeTm) {
//            setData(newValue.getValue());
//        }
//    }
//    private void setData(EmployeeTm newValue) {
//        if (newValue != null) {
//            txtSearchEmpID.setEditable(false);
//            txtEmpName.setText(newValue.getEmpName());
//            txtEmpEmail.setText(newValue.getEmpEmail());
//            txtEmpContNum.setText(newValue.getEmpContNum());
//            txtEmpAddress.setText(newValue.getEmpAddress());
//            txtEmpPassword.setText(newValue.get);
//        }
//    }
//
//    public void backBtnOnAction(ActionEvent actionEvent) {
//    }
//
////    public void updateBtnOnAction(ActionEvent actionEvent) {
////        boolean isUpdated = employeeBo.updateEmployee(new EmployeeDto(
////                txtSearchEmpID.getText(),
////                txtEmpName.getText(),
////                txtEmpEmail.getText(),
////                txtEmpContNum.getText(),
////                txtEmpAddress.getText()
////        ));
////    }
//
//
//    public void updateBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        boolean isUpdate = employeeBo.updateEmployee(new EmployeeDto(
//                txtSearchEmpID.getText(),
//                txtEmpName.getText(),
//                txtEmpEmail.getText(),
//                txtEmpContNum.getText(),
//                txtEmpAddress.getText(),
//                txtEmpPassword.getText()
//
//        ));
//        if (isUpdate){
//            new Alert(Alert.AlertType.INFORMATION,"Employee Updated").show();
//            loadEmployeeTable();
////            clearFields();
//        }
//    }
//
//    private void loadEmployeeTable() {
//        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();
//        try {
//            List<EmployeeDto> dtoList = employeeBo.allEmployees();
//            for (EmployeeDto dto : dtoList) {
//                Button btn = new Button("Delete");
//                EmployeeTm employee = new EmployeeTm(
//                        dto.getEmployeeId(),
//                        dto.getEmployeeName(),
//                        dto.getEmployeeEmail(),
//                        dto.getEmployeeContNumber(),
//                        dto.getEmployeeAddress(),
//                        dto.getEmployeePassword(),
//                        btn
//                );
//                btn.setOnAction(actionEvent -> deleteEmployee(employee.getEmpId()));
//                tmList.add(employee);
//            }
//            RecursiveTreeItem<EmployeeTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
//            tblEmployeeDetails.setRoot(treeItem);
//            tblEmployeeDetails.setShowRoot(false);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void deleteEmployee(String empId) {
//
//    }
//}
