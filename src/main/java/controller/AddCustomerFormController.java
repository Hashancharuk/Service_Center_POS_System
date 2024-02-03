package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddCustomerFormController {
    public AnchorPane customerPane;
    public TextField txtCostName;
    public TextField txtCustEmail;
    public TextField txtCostID;
    public TextField txtCustContNum;
    public TextField txtCustAddress;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colContNum;
    public TableColumn colAddress;
    public TableColumn colOption;
    public TableView tableCustomer;

    public CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    public Label lablCustId;

    public void initialize() throws ClassNotFoundException{
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContNum.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();
        generateCustomerId();
        tableCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setData((CustomerTm)newValue);
        }));
    }
    int num;
    private void generateCustomerId() {
        try {
            CustomerDto dto = customerBo.lastOrder();
            if (dto != null) {
                String id = dto.getId();
                num = Integer.parseInt(id.split("C")[1]) + 1;
                lablCustId.setText(String.format("C%03d", num));
            } else {
                num = 1;
                lablCustId.setText(String.format("C%03d", num));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> dtoList = customerBo.allCustomers();
            for (CustomerDto dto : dtoList) {
                Button btn = new Button("Delete");
                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getContactNumber(),
                        dto.getAddress(),
                        btn
                );
                btn.setOnAction(actionEvent -> deleteCustomer(c.getId()));
                tmList.add(c);
            }
            tableCustomer.setItems(tmList); // Add this line to set data to the TableView
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void deleteCustomer(String id) {
        try {
            boolean isDeleted = customerBo.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadCustomerTable();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Something Went Wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null){
            txtCostID.setEditable(false);
            txtCostID.setText(newValue.getId());
            txtCostName.setText(newValue.getName());
            txtCustEmail.setText(newValue.getEmail());
            txtCustContNum.setText(newValue.getContactNumber());
            txtCustAddress.setText(newValue.getAddress());
            lablCustId.setText("");
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            if (customerBo != null) {
                boolean isSaved = customerBo.saveCustomer(new CustomerDto(
                        lablCustId.getText(),
                        txtCostName.getText(),
                        txtCustEmail.getText(),
                        txtCustContNum.getText(),
                        txtCustAddress.getText()

                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved").show();
                    clearFields();
                    loadCustomerTable();
                    lablCustId.setText(String.format("C%03d", ++num));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "CustomerBo is null").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) customerPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = customerBo.updateCustomer(new CustomerDto(
                    txtCostID.getText(),
                    txtCostName.getText(),
                    txtCustEmail.getText(),
                    txtCustContNum.getText(),
                    txtCustAddress.getText()
            ));
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
                loadCustomerTable();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        tableCustomer.refresh();
        txtCostID.clear();
        txtCostName.clear();
        txtCustEmail.clear();
        txtCustContNum.clear();
        txtCustAddress.clear();
        lablCustId.setText("");
    }

    public void reloadButtonOnAction(ActionEvent actionEvent) {
        loadCustomerTable();
        tableCustomer.refresh();
    }

}
