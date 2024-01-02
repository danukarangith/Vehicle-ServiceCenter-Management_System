package lk.ijse.vehiServePro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.StocksDTO;
import lk.ijse.vehiServePro.model.CustomerModel;
import lk.ijse.vehiServePro.model.StockModel;

import java.sql.SQLException;

public class AddStockFormController {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtRemain;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String remain =txtRemain.getText();
        String user = txtUser.getText();


        var dto = new StocksDTO(id, name, price,remain, user);

        var model = new StockModel();
        try {
            boolean isSaved = model.saveItem(dto);
            //tblStocks.refresh();
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String remain = txtRemain.getText();
        String user = txtUser.getText();


        System.out.println(id);

        StocksDTO dto = new StocksDTO(id, name, price,remain, user);


        var model = new StockModel();
        try {

            boolean isUpdated = model.updateStock(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var stockModel = new StockModel();
        try {
            boolean isDeleted = stockModel.deleteStock(id);

            if (isDeleted) {
                //tblStock.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted! ").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void clearFields() {
        txtId.clear();
        txtName.setText("");
        txtPrice.setText("");
        txtRemain.setText("");
        txtUser.setText("");
    }
}
