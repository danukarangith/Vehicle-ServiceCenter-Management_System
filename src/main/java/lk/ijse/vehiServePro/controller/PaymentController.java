package lk.ijse.vehiServePro.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.PaymentDTO;
import lk.ijse.vehiServePro.dto.VehicleDTO;
import lk.ijse.vehiServePro.model.PaymentModel;
import lk.ijse.vehiServePro.model.VehicleModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class PaymentController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAmount;

    @FXML
    private RadioButton txtMethod;

    @FXML
    private TextField txtDetail;

    @FXML
    private ImageView myImage;



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
        String name = txtName.getText();
        String amount = txtAmount.getText();
        String method = txtMethod.getText();
        String detail = txtDetail.getText();

        var dto = new PaymentDTO(id,name,amount,method,detail);

        var model = new PaymentModel();
        try{
            boolean isSaved = model.savePayment(dto);
            //tblCustomer.refresh();
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Details Save!").show();
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
        String amount = txtAmount.getText();
        String method = txtMethod.getText();
        String detail = txtDetail.getText();


        System.out.println(id);

        var dto = new PaymentDTO(id,name,amount,method,detail);


        var model = new PaymentModel();
        try{

            boolean isUpdated = model.updatePayment(dto);
            System.out.println(isUpdated);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Details Update!").show();
                clearFields();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        var paymentModel = new PaymentModel();
        try{
            boolean isDeleted =paymentModel.deletePayment(id);

            if(isDeleted){
                // tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Details Delete! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void clearFields() {
        txtId.clear();
        txtName.setText("");
        txtAmount.setText("");
        txtDetail.setText("");

    }

    public void printOnAction(ActionEvent event) {
        //System.out.println("HELLO");
        InputStream resource = this.getClass().getResourceAsStream("/reports/Blank_A4_Landscape.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
