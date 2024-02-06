package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemCategoryBo;
import bo.custom.PlaceOrderDetailBo;
import bo.custom.impl.ItemCategoryBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemCategoryDto;
import dto.PlaceOrderDetailDto;
import dto.tm.PlaceOrderDetailsTm;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {
    public TextField txtOrderId;
    public TextField txtCostName;
    public TextField txtDate;
    public TextField txtFault;
    public TextField txtCustId;
    public JFXComboBox cmbItem;
    public TextField txtQty;
    public JFXComboBox cmbItemCategory;
    public TreeTableColumn colOrderId;
    public TreeTableColumn colCustId;
    public TreeTableColumn colName;
    public TreeTableColumn colItemCategory;
    public TreeTableColumn colItemName;
    public TreeTableColumn colDate;
    public TreeTableColumn colQty;
    public TreeTableColumn colFault;
    public TreeTableColumn colOption;
    public Label lblTime;
    public Label lblDate;
    public JFXTreeTableView tableOrderDetails;
    public TextField txtSearchCustId;
    public TextField txtCustID;
    public JFXComboBox cmbCustID;
    public AnchorPane placeOrderFormPane;
    public Label labelOrderID;
    public TextField txtCustEmail;
    public TextField txtCustNumber;
    public TreeTableColumn colEmail;
    public TreeTableColumn colNumber;

    private List<ItemCategoryDto> itemCategory;
    private List<CustomerDto> customers;
//    private CustomerBo customerBo = new CustomerBoImpl();
    private ItemCategoryBo itemCategoryBo = new ItemCategoryBoImpl();

    private PlaceOrderDetailBo placeOrderDetailBo = BoFactory.getInstance().getBo(BoType.PLACEORDER);
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void addBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) placeOrderFormPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            boolean isSaved = placeOrderDetailBo.saveOrder(new PlaceOrderDetailDto(
                    labelOrderID.getText(),
                    txtCustEmail.getText(),
                    txtCustNumber.getText(),
                    txtCostName.getText(),
                    cmbItemCategory.getSelectionModel().getSelectedItem().toString(),
                    cmbItem.getSelectionModel().getSelectedItem().toString(),
                    txtDate.getText(),
                    Integer.parseInt(txtQty.getText()),
                    txtFault.getText()
            ));
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed!").show();
                loadPlaceOrderDetails();
                clearFields();
                labelOrderID.setText(String.format("OR%03d",++num));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        tableOrderDetails.refresh();
        txtOrderId.clear();
        txtCostName.clear();
        cmbItemCategory.getSelectionModel().clearSelection();
        cmbItem.getSelectionModel().clearSelection();
        txtQty.clear();
        txtFault.clear();
        labelOrderID.setText("");
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {

    }
    public void initialize() throws SQLException, ClassNotFoundException {
        generateOrderID();
        loadDateAndTime();
        loadItemCategory();
        loadItems();
        loadCustomerID();
        loadPlaceOrderDetails();
        cmbCustID.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, id) ->{
            for (CustomerDto dto: customers) {
                if (dto.getId().equals(id)){
                    txtCostName.setText(dto.getName());
                    txtCustEmail.setText(dto.getEmail());
                    txtCustNumber.setText(dto.getContactNumber());
                }
            }
        }));

        cmbItemCategory.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, category) -> {
            for (ItemCategoryDto dto:itemCategory) {
                if (dto.getCategory().equals(category)){
                    cmbItem.setValue(dto.getName());
                }
            }
        }));
        colOrderId.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colNumber.setCellValueFactory(new TreeItemPropertyValueFactory<>("contactNumber"));
        colName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        colItemCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCategory"));
        colItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemName"));
        colDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colFault.setCellValueFactory(new TreeItemPropertyValueFactory<>("fault"));

        List<PlaceOrderDetailsTm> placeOrderDetailsList = loadPlaceOrderDetails();
        ObservableList<PlaceOrderDetailsTm> observableList = FXCollections.observableArrayList(placeOrderDetailsList);
        RecursiveTreeItem<PlaceOrderDetailsTm> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);

        tableOrderDetails.setRoot(root);
        tableOrderDetails.setShowRoot(false);
        tableOrderDetails.getSelectionModel().selectedItemProperty().addListener(this::changed);
    }
    int num;
    private void generateOrderID() {
        try {
            PlaceOrderDetailDto dto = placeOrderDetailBo.lastOrderCode();
            if (dto != null) {
                String id = dto.getId();
                num = Integer.parseInt(id.split("OR")[1]) + 1;
                labelOrderID.setText(String.format("OR%03d", num));
                System.out.println("Last Order Code: " + dto.getId());
            } else {
                num = 1;
                labelOrderID.setText(String.format("OR%03d", num));
                System.out.println("No Previous Order Code found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void changed(Observable observable) {

    }

    private List<PlaceOrderDetailsTm> loadPlaceOrderDetails() {
        ObservableList<PlaceOrderDetailsTm> tmList = FXCollections.observableArrayList();
        List<PlaceOrderDetailDto> dtoList = null;
        try {
            dtoList = placeOrderDetailBo.allPlaceOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (dtoList != null) {
            for (PlaceOrderDetailDto dto : dtoList) {
                Button btn = new Button("Delete");
                PlaceOrderDetailsTm po = new PlaceOrderDetailsTm(
                        dto.getOrderId(),
                        dto.getEmail(),
                        dto.getContactNumber(),
                        dto.getName(),
                        dto.getItemCategory(),
                        dto.getItemName(),
                        dto.getDate(),
                        dto.getQty(),
                        dto.getFault(),
                        btn
                );
                btn.setOnAction(actionEvent -> deleteOrders(po.getOrderId()));
                tmList.add(po);
            }
            RecursiveTreeItem<PlaceOrderDetailsTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tableOrderDetails.setRoot(treeItem);
            tableOrderDetails.setShowRoot(false);
        }

        return tmList;
    }



    private void deleteOrders(String orderId) {

    }

    private void setData(PlaceOrderDetailsTm newValue) {
        if (newValue != null){
            txtOrderId.setText(newValue.getOrderId());
            txtCustEmail.setText(newValue.getEmail());
            txtCustNumber.setText(newValue.getContactNumber());
            txtCostName.setText(newValue.getName());
            cmbItemCategory.getSelectionModel().getSelectedItem().toString();
            cmbItem.getSelectionModel().getSelectedItem().toString();
            txtDate.setText(String.valueOf(newValue.getDate()));
            txtQty.setText(String.valueOf(newValue.getQty()));
            txtFault.setText(newValue.getFault());
        }
    }

    private void loadCustomerID() throws SQLException, ClassNotFoundException {
        customers = customerBo.allCustomers();
        ObservableList list = FXCollections.observableArrayList();
        for (CustomerDto dto:customers) {
            list.add(dto.getId());
        }
        cmbCustID.setItems(list);
    }

    private void loadItems() {
        try {
            itemCategory = itemCategoryBo.allItems();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemCategoryDto dto:itemCategory) {
                list.add(dto.getCategory());
            }
            cmbItem.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadItemCategory() {
        try {
            itemCategory = itemCategoryBo.allItems();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemCategoryDto dto:itemCategory) {
                list.add(dto.getName());
            }
            cmbItemCategory.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Date date;
    private void loadDateAndTime() {
            date = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            txtDate.setText(f.format(date));
//            txtDate.setText(f.format(date));

//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.ZERO,
//                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
//        ), new KeyFrame(Duration.seconds(1)));
//
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) placeOrderFormPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
