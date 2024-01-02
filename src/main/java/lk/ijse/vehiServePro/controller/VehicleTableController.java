package lk.ijse.vehiServePro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vehiServePro.dto.StocksDTO;
import lk.ijse.vehiServePro.dto.VehicleDTO;

import lk.ijse.vehiServePro.dto.tm.VehicleTm;
import lk.ijse.vehiServePro.model.StockModel;
import lk.ijse.vehiServePro.model.VehicleModel;

import java.sql.SQLException;
import java.util.List;

public class VehicleTableController {

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colBrand;
    @FXML
    private TableColumn<?,?> colCid;




    @FXML
    private TableColumn<?, ?> colType;



    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }



    private void setCellValueFactory(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("customer id"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    private void loadAllCustomer(){
        var model = new VehicleModel();

        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();

        try{
            List<VehicleDTO> dtoList = model.getAllVehicle();

            for (VehicleDTO dto : dtoList){
                obList.add(
                        new VehicleTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getNum(),
                                dto.getBrand(),
                                dto.getType()
                        )
                );
            }
            tblVehicle.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
