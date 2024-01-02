package lk.ijse.vehiServePro.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.vehiServePro.dto.SupplierDTO;
import lk.ijse.vehiServePro.dto.VehicleDTO;
import lk.ijse.vehiServePro.model.SupplierModel;
import lk.ijse.vehiServePro.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;

public class StockSupplierFormController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    void btnSaveOnAction(ActionEvent event) {


        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();


        var dto = new SupplierDTO(id,name,address);

        var model = new SupplierModel();
        try{
            boolean isSaved = model.saveSupplier(dto);
            //tblCustomer.refresh();
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Saved!").show();
                clearFields();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        System.out.println(id);

        SupplierDTO dto = new SupplierDTO(id,name,address);


        var model = new SupplierModel();
        try{

            boolean isUpdated = model.updateSupplier(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated!").show();
                clearFields();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var supplierModell = new SupplierModel();
        try{
            boolean isDeleted =supplierModell.deleteSupplier(id);

            if(isDeleted){
                // tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void clearFields() {
        txtId.clear();
        txtName.setText("");
        txtAddress.setText("");

    }



}
