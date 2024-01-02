package lk.ijse.vehiServePro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.SupplierDTO;
import lk.ijse.vehiServePro.dto.tm.CustomerTm;
import lk.ijse.vehiServePro.dto.tm.SupplierDetailTm;
import lk.ijse.vehiServePro.model.CustomerModel;
import lk.ijse.vehiServePro.model.SupplierModel;

import java.sql.SQLException;
import java.util.List;

public class supplierDetailsController {
    @FXML
    private TableView<SupplierDetailTm> tblSupplier;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }



    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }
    private void loadAllCustomer(){
        var model = new SupplierModel();

        ObservableList<SupplierDetailTm> obList = FXCollections.observableArrayList();

        try{
            List<SupplierDTO> dtoList = model.getAllSupplier();

            for (SupplierDTO dto : dtoList){
                obList.add(
                        new SupplierDetailTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress()

                        )
                );
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
