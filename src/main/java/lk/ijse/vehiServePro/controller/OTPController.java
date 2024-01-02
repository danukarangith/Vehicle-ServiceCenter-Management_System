package lk.ijse.vehiServePro.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class OTPController {
    @FXML
    private TextField txtName;

    @FXML
    private void validateOTP() throws IOException {
        String otp = txtName.getText();

        if (otp.equals("1926")) {
            showAlert("OTP Correct", "OTP is correct.");
            navigateToMainWindow();

        } else {
            showAlert("OTP Incorrect", "OTP is incorrect. Please try again.");
            clearFields();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void navigateToMainWindow() throws IOException {
        // homepage.getChildren().clear();


        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);



        //Stage primaryStage = (Stage) rootNode.getScene().getWindow();
        Stage primaryStage = new Stage();


        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
       // primaryStage.setTitle("Main Window");
        primaryStage.show();


    }
    @FXML
    void clearFields() {
        txtName.clear();
    }



}
