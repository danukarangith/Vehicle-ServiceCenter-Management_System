package lk.ijse.vehiServePro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vehiServePro.Utill.EmailSender;
import lk.ijse.vehiServePro.dto.*;
import lk.ijse.vehiServePro.dto.tm.ReservationTm;
import lk.ijse.vehiServePro.model.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ReservationFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtVehNum;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTime;

    @FXML
    private TableView<ReservationTm> tblResrvation;

    @FXML
    private TableColumn<?,?> colId;

    @FXML
    private  TableColumn<?,?> colEmail;

    @FXML
    private  TableColumn<?,?> colVehNum;

    @FXML
    private TableColumn<?,?> colDate;

    @FXML
    private TableColumn<?,?> colTime;


   /* public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colVehNum.setCellValueFactory(new PropertyValueFactory<>("vehicle number"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    private void loadAllCustomer(){
        var model = new ReservationModel();

        ObservableList<ReservationTm> obList = FXCollections.observableArrayList();

        try{
            List<ReservationDTO> dtoList = model.getAllReservation();

            for (ReservationDTO dto : dtoList){
                obList.add(
                        new ReservationTm(
                                dto.getId(),
                                dto.getEmail(),
                                dto.getVehNum(),
                                dto.getDate(),
                                dto.getTime()
                        )
                );
            }
            tblResrvation.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/



    @FXML
    void btnSaveOnAction(ActionEvent event) {


        String id = txtId.getText();
        String email = txtEmail.getText();
        String vehNum = txtVehNum.getText();
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText();

        boolean isValidate = validateCustomer();

        if (isValidate) {
            var dto = new ReservationDTO(id,email,vehNum,date,time);

            var model = new ReservationModel();
            try {
                boolean isSaved = model.saveReservation(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Resrvation Add!").show();
                    clearFields();
                    //initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private boolean validateCustomer() {

        String idText = txtEmail.getText();

        boolean isCustomerIDValidation = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", idText);

        if (!isCustomerIDValidation) {

            new Alert(Alert.AlertType.ERROR, "INVALID EMAIL ADDRESS").show();
            txtEmail.setStyle("-fx-border-color: Red");

            return false;

        }



        return  true;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String email = txtEmail.getText();
        String vehNum = txtVehNum.getText();
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText();


        System.out.println(id);

        var dto = new ReservationDTO(id,email,vehNum,date,time);


        var model = new ReservationModel();
        try{

            boolean isUpdated = model.updateReservation(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Report Updated!").show();
                clearFields();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var reservationModel = new ReservationModel();
        try{
            boolean isDeleted = reservationModel.deleteReservation(id);

            if(isDeleted){
                // tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Report deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void clearFields() {
        txtId.clear();
       //da txtEmail.setText("");
        txtVehNum.setText("");
        // txtDate.setTooltip("");
        txtTime.setText("");
        //txtDate.getValue("");

    }

    public void sendEmailOnAction(ActionEvent event) throws MessagingException, IOException {

        EmailSender emailSender = new EmailSender();

        String emailBody = "Your reservation done! Drive the vehicle on time to the Service. (we ready to give you best customer service and welcome) LEO CARE GALLE";
        String emailSubject = "Your Reservation Successfull ";
        String emailAddress = txtEmail.getText();
        emailSender.sendMail(emailAddress,emailBody,emailSubject);




    }
}





