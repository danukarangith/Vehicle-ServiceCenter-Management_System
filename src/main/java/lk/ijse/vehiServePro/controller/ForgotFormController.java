package lk.ijse.vehiServePro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.Utill.EmailSender;

import javax.mail.MessagingException;
import java.io.IOException;

public class ForgotFormController {
    @FXML
    private AnchorPane signupcontext;

    @FXML
    private TextField txtSendEmail;
    @FXML
    void gotoSigninPage(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signinPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signupcontext.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("vehiServePro");
        stage.centerOnScreen();
    }

    public void sendEmailOnAction(ActionEvent event) throws MessagingException, IOException {

        EmailSender emailSender = new EmailSender();

        String emailBody = "1926";
        String emailSubject = "OTP CODE ";
        String emailAddress = txtSendEmail.getText();
        emailSender.sendMail(emailAddress,emailBody,emailSubject);

        Otppage(event);


    }


    @FXML
    void Otppage(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OTPCodeForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signupcontext.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("vehiServePro");
        stage.centerOnScreen();
    }


}
