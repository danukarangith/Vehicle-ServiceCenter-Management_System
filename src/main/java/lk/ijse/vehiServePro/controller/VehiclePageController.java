package lk.ijse.vehiServePro.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.VehicleDTO;
import lk.ijse.vehiServePro.model.CustomerModel;
import lk.ijse.vehiServePro.model.VehicleModel;


public class VehiclePageController  {





        @FXML
        private ImageView myImage;



        @FXML
        private TextField txtId;

        @FXML
        private TextField txtCid;

        @FXML
        private TextField txtNum;

        @FXML
        private TextField txtBrand;

        @FXML
        private TextField txtType;



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(myImage);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        //translate.setByY(-250);
        translate.setAutoReverse(true);
        translate.play();

        String id = txtId.getText();
        String name = txtCid.getText();
        String num = txtNum.getText();
        String brand = txtBrand.getText();
        String type = txtType.getText();

        var dto = new VehicleDTO(id,name,num,brand,type);

        var model = new VehicleModel();
        try{
            boolean isSaved = model.saveVehicle(dto);
            //tblCustomer.refresh();
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Saved!").show();
                //clearFields();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtCid.getText();
        String num = txtNum.getText();
        String brand = txtBrand.getText();
        String type = txtType.getText();

        System.out.println(id);

        VehicleDTO dto = new VehicleDTO(id,name,num,brand,type);


        var model = new VehicleModel();
        try{

            boolean isUpdated = model.updateVehicle(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Updated!").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var vehicleModel = new VehicleModel();
        try{
            boolean isDeleted =vehicleModel.deleteCustomer(id);

            if(isDeleted){
               // tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void tableButtonAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/vehicleTableForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }





}
