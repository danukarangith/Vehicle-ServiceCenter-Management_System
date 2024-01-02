package lk.ijse.vehiServePro.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.scene.control.Label;
import lk.ijse.vehiServePro.dto.ReservationDTO;
import lk.ijse.vehiServePro.dto.tm.ReservationTm;
import lk.ijse.vehiServePro.model.ReservationModel;


public class dashboardFormController {
    @FXML
    public AnchorPane dash;




    @FXML
    void gotoCustomerForm(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customerForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }
    @FXML
    void gotoDashbord(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) dash.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.centerOnScreen();
    }
    @FXML
    void gotofirstPage(ActionEvent event)throws IOException {
        SigninPageFormController.close(event);
       /* AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signinPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) dash.getScene().getWindow();

       stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.centerOnScreen();*/
    }
    @FXML
    void gotoVehicleForm(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vehicleForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }
    @FXML
    void gotoEmployeeForm(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employeeForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }

    @FXML
    void gotoStockForm(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stockForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }
    @FXML
    void tableButtonAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/calculterForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    void notepadbuttonAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/notepadForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    void hotlineAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/problemForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       // stage.centerOnScreen();
    }
    @FXML
    void gotoServiceDetail(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/serviceDetailForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }
    @FXML
    void goReservation(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reservationForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }


    @FXML
    void goPayment(ActionEvent event) throws IOException {
        dash.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/paymentForm.fxml"));
        Parent root = loader.load();
        dash.getChildren().add(root);
    }
    @FXML
    private Label clock;
    @FXML
    private Label dateSet;

    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    LocalTime time = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    String formattedTime = time.format(formatter);
                    clock.setText(formattedTime);
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
                    String formattedDate = date.format(formatter);
                    dateSet.setText(formattedDate);
                }),
                new KeyFrame(Duration.minutes(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setCellValueFactory();
        loadAllCustomer();

    }


    @FXML
    void emailonAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/emailForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }


    @FXML
    private TableView<ReservationTm> tblResrvation;

    @FXML
    private TableColumn<?,?> colId;

    @FXML
    private  TableColumn<?,?> colEmail;

    @FXML
    private  TableColumn<?,?> colvehNum;

    @FXML
    private TableColumn<?,?> colDate;

    @FXML
    private TableColumn<?,?> colTime;





    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colvehNum.setCellValueFactory(new PropertyValueFactory<>("vehNum"));
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
    }
}
