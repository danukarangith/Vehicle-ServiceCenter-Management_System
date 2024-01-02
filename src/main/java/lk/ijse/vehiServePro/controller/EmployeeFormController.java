package lk.ijse.vehiServePro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.EmployeeDTO;
import lk.ijse.vehiServePro.model.CustomerModel;
import lk.ijse.vehiServePro.model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;





public class EmployeeFormController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtUser;

    @FXML
    private AnchorPane dash;



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String id = txtId.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String type = txtType.getText();
        String user = txtUser.getText();

        boolean isValidate = validateEmployee();

        if (isValidate) {
            var dto = new EmployeeDTO(name,id,contact,address,type,user);

            var model = new EmployeeModel();
            try {
                boolean isSaved = model.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    clearFields();
                    //initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean validateEmployee() {

        String idText = txtId.getText();

        boolean isEmployeeIDValidation = Pattern.matches("[C][0-9]{3,}", idText);

        if (!isEmployeeIDValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID Employee ID").show();
            txtId.setStyle("-fx-border-color: Red");

            return false;

        }




        String addressText = txtAddress.getText();

        boolean isEmployeeAddressValidation = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", addressText);

        if (!isEmployeeAddressValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID Employee address").show();
            txtAddress.setStyle("-fx-border-color: Red");

            return false;

        }

        String telText = txtContact.getText();

        boolean isCustomerTelValidation = Pattern.matches("[0-9]{10}", telText);

        if (!isCustomerTelValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID CUSTOMER tel").show();
            txtContact.setStyle("-fx-border-color: Red");
            return false;
        }

        return  true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String id = txtId.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String type = txtType.getText();
        String user = txtUser.getText();


        System.out.println(id);

        var dto = new EmployeeDTO(name,id,contact,address,type,user);


        var model = new EmployeeModel();
        try{

            boolean isUpdated = model.updateEmployee(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated!").show();
                clearFields();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtName.getText();

        var EmployeeModel = new EmployeeModel();
        try{
            boolean isDeleted = EmployeeModel.deleteCustomer(id);

            if(isDeleted){
                //tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeTable.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    void clearFields() {
        txtId.clear();
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtType.setText("");
        txtUser.setText("");
    }
}
