package lk.ijse.vehiServePro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.ServiceDetailDTO;


import lk.ijse.vehiServePro.model.ServiceDetailModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;

public class ServiceDetailsController {

    @FXML
    private TextField  txtCuName;
    @FXML
    private TextField txtNumber;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtTime;
    @FXML
    private  TextField txtDetail;
    @FXML
    private  TextField txtEmName;
    @FXML
    private TextField txtId;



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String Cuname = txtCuName.getText();
        String number = txtNumber.getText();
        String date = String.valueOf(txtDate.getValue());
        String time =txtTime.getText();
        String detail = txtDetail.getText();
        String Ename = txtEmName.getText();


        var dto = new ServiceDetailDTO(id,Cuname,number,date,time,detail,Ename);

        var model = new ServiceDetailModel();
        try {
            boolean isSaved = model.saveDetail(dto);
            //tblStocks.refresh();
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Report Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String Cuname = txtCuName.getText();
        String number = txtNumber.getText();
        String date = String.valueOf(txtDate.getValue());
        String time =txtTime.getText();
        String detail = txtDetail.getText();
        String Ename = txtEmName.getText();


        System.out.println(id);

        var dto = new ServiceDetailDTO(id,Cuname,number,date,time,detail,Ename);


        var model = new ServiceDetailModel();
        try{

            boolean isUpdated = model.updateDetail(dto);
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

        var serviceDetailModel = new ServiceDetailModel();
        try{
            boolean isDeleted = serviceDetailModel.deleteDetail(id);

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
        txtCuName.setText("");
        txtNumber.setText("");
       // txtDate.setTooltip("");
        txtTime.setText("");
        txtDetail.setText("");
        txtCuName.setText("");
    }


    public void printOnAction(ActionEvent event) {
        //System.out.println("HELLO");
        InputStream resource = this.getClass().getResourceAsStream("/reports/SERVICE _REPO.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
