package lk.ijse.vehiServePro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.dto.UserDTO;
import lk.ijse.vehiServePro.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class SignupPageFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPasswrd;

    @FXML
    private TextField txtName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String userName = txtName.getText();
        String password = txtPasswrd.getText();

        UserDTO dto = new UserDTO(userName,email,password);
        try {
            boolean isSaved = new UserModel().saveCustomer(dto);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Registration Succesfull").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    private AnchorPane signupcontext;




    @FXML
    void gotoSigninPage(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signinPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signupcontext.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("vehiServePro");
        stage.centerOnScreen();
    }
    void clearFields() {
        txtPasswrd.setText("");
        txtName.setText("");
        txtEmail.setText("");

    }
}
