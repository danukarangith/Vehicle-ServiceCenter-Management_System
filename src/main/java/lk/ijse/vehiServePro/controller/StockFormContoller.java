package lk.ijse.vehiServePro.controller;

import javafx.animation.TranslateTransition;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.vehiServePro.dto.EmployeeDTO;
import lk.ijse.vehiServePro.dto.StocksDTO;
import lk.ijse.vehiServePro.dto.tm.EmployeeTm;
import lk.ijse.vehiServePro.dto.tm.StockTm;
import lk.ijse.vehiServePro.model.EmployeeModel;
import lk.ijse.vehiServePro.model.StockModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StockFormContoller {

    @FXML
    private ImageView myImage;


    @FXML
    void gotoAddStocks(ActionEvent event) throws IOException {

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(myImage);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        //translate.setByY(-250);
        translate.setAutoReverse(true);
        translate.play();

        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/addStocksForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?,?> colRemain;




    @FXML
    private TableColumn<?, ?> colUser;



    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }



    private void setCellValueFactory(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("remain"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("user"));
    }
    private void loadAllCustomer(){
        var model = new StockModel();

        ObservableList<StockTm> obList = FXCollections.observableArrayList();

        try{
            List<StocksDTO> dtoList = model.getAllStock();

            for (StocksDTO dto : dtoList){
                obList.add(
                        new StockTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getRemain(),
                                dto.getUser()
                        )
                );
            }
            tblStock.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void gotoSupplier(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/stockSupplierFome.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    void gotoSupplierDetails(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/supplierDetailsForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}

