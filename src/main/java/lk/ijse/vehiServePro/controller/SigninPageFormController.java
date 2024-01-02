package lk.ijse.vehiServePro.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vehiServePro.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

import static com.jfoenix.svg.SVGGlyphLoader.clear;


public class SigninPageFormController {
         @FXML
         public AnchorPane homepage;


        @FXML
        private TextField txtUserName;
        @FXML
        private PasswordField txtPassword;

        private static Stage stage;

        @FXML
        private AnchorPane root;



    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String password = txtPassword.getText();
        String userName = txtUserName.getText();

        try{
            boolean b = new UserModel().isExistUser (userName,password);
            if(b){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Welcome User Have a Nice Day");
                alert.show();

                txtPassword.clear();
                txtUserName.clear();
                navigateToMainWindow();
                alert.close();
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



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
    public static void close(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        //stage.show();
    }





    @FXML
      void gotoSignupPage(ActionEvent event)throws IOException {
          AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signupPageForm.fxml"));
          Scene scene = new Scene(anchorPane);
          Stage stage = (Stage) homepage.getScene().getWindow();

          stage.setScene(scene);
          stage.setTitle("Sign up");
          stage.centerOnScreen();
      }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) throws IOException {
        Parent achorPane = FXMLLoader.load(this.getClass().getResource("/view/forgotForm.fxml"));

        Scene scene = new Scene(achorPane);
        Stage stage = (Stage) homepage.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Forgot Password page");
        stage.centerOnScreen();
    }

    @FXML

    void appStopOnAction (ActionEvent event) throws IOException{
        System.exit(0);
    }



}




