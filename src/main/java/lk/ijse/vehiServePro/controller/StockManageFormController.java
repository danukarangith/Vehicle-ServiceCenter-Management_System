package lk.ijse.vehiServePro.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.StockManageDTO;
import lk.ijse.vehiServePro.dto.StocksDTO;
import lk.ijse.vehiServePro.dto.UserDTO;
import lk.ijse.vehiServePro.dto.tm.CartTm;
import lk.ijse.vehiServePro.model.StockManageModel;
import lk.ijse.vehiServePro.model.StockModel;
import lk.ijse.vehiServePro.model.SupplierModel;
import lk.ijse.vehiServePro.model.UserModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockManageFormController {/*
    private final UserModel userModel = new UserModel();
    private final StockModel stockModel = new StockModel();
    private final SupplierModel supplierModel = new SupplierModel();
    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnPlaceorder;

    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private JFXComboBox<String> cmbItemId;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colItemName;
    @FXML
    private TableColumn<?, ?> colItemId;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblOrderDate;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<CartTm> tblOrderCart;
    @FXML
    private TextField txtQty;
    @FXML
    private Label lblNetTotal;

    private final StockManageModel stockManageModel = new StockManageModel();

    public void initialize() {
        setCellValueFactory();
        generateNextOrderId();
        setDate();
        loadCustomerIds();
        loadItemIds();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String orderId = UserModel.generateNextUserName;
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StocksDTO> itemList = StockModel.loadAllStocks();

            for (StocksDTO stocksDTO : itemList) {
                obList.add(stocksDTO.getStockId());
            }

            cmbStockId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<UserModel> cusList = userModel.loadAllUser();

            for (UserDTO dto : cusList) {
                obList.add(dto.getName());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblOrderDate.setText(date);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemId = cmbItemId.getValue();
        String itemName = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        int hand = Integer.parseInt(lblQtyOnHand.getText());
        if (qty < hand) {
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());

            double total = qty * unitPrice;
            Button btn = new Button("remove");
            btn.setCursor(Cursor.HAND);

            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                    obList.remove(index);
                    tblOrderCart.refresh();

                    calculateNetTotal();
                }
            });

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (itemId.equals(colItemId.getCellData(i))) {
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTot(total);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                    return;
                }
            }

            obList.add(new CartTm(
                    itemId,
                    itemName,
                    qty,
                    unitPrice,
                    total,
                    btn
            ));

            tblOrderCart.setItems(obList);
            calculateNetTotal();
            txtQty.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "The stock haven't ").show();
            txtQty.setStyle("-fx-border-color: Red");

        }
    }

    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }

        lblNetTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String userName = lblOrderId.getText();
        String supplierId = cmbCustomerId.getValue();
        LocalDate date = LocalDate.parse(lblOrderDate.getText());

        List<CartTm> tmList = new ArrayList<>();

        for (CartTm cartTm : obList) {
            tmList.add(cartTm);
        }

        var stockManageDTO = new StockManageDTO(
                userName,
                supplierId,

                tmList
        );

        try {
            boolean isSuccess = StockManageModel.stockManage(StockManageDTO);
            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String stockId = cmbStockId.getValue();

        txtQty.requestFocus();

        try {
            StocksDTO dto = StockModel.searchStock(stockId);

            lblDescription.setText(dto.getStockName());
            lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String username = cmbCustomerId.getValue();
        UserDTO dto = UserModel.searchUser(username);

        lblCustomerName.setText(dto.getName());
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Customer Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }*/

}
