package lk.ijse.vehiServePro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.Utill.EmailSender;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.tm.CustomerTm;
import lk.ijse.vehiServePro.model.CustomerModel;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {
    @FXML
    private TableView<CustomerTm> tblCustomer;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private AnchorPane root;




    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSendEmail;


    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }



    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    private void loadAllCustomer(){
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try{
            List<CustomerDTO> dtoList = model.getAllCustomers();

            for (CustomerDTO dto : dtoList){
                obList.add(
                        new CustomerTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getContact(),
                                dto.getEmail()
                        )
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        boolean isValidate = validateCustomer();

        if (isValidate) {
            var dto = new CustomerDTO(id, name, address,contact,email);

            var model = new CustomerModel();
            try {
                boolean isSaved = model.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean validateCustomer() {

        String idText = txtId.getText();

        boolean isCustomerIDValidation = Pattern.matches("[C][0-9]{3,}", idText);

        if (!isCustomerIDValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID CUSTOMER ID").show();
            txtId.setStyle("-fx-border-color: Red");

            return false;

        }




        String addressText = txtAddress.getText();

        boolean isCustomerAddressValidation = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", addressText);

        if (!isCustomerAddressValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID CUSTOMER address").show();
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
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        System.out.println(id);

        CustomerDTO  dto = new CustomerDTO(id,name,address,email,contact);


        var model = new CustomerModel();
        try{

            boolean isUpdated = model.updateCustomer(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated!").show();
                clearFields();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var customerModel = new CustomerModel();
        try{
            boolean isDeleted = customerModel.deleteCustomer(id);

            if(isDeleted){
                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"customer deleted! ").show();
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
        txtContact.setText("");
        txtEmail.setText("");
    }



    public void sendEmailOnAction(ActionEvent event) throws MessagingException {
        EmailSender emailSender = new EmailSender();

        String emailBody = "You are successfully register leo auto care service system,we inform you next service date ";
        String emailSubject = "leo service ";
        String emailAddress = txtSendEmail.getText();
        emailSender.sendMail(emailAddress,emailBody,emailSubject);
    }
}





