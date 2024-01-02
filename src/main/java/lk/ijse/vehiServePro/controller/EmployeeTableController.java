package lk.ijse.vehiServePro.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vehiServePro.dto.EmployeeDTO;
import lk.ijse.vehiServePro.dto.EmployeeDTO;
import lk.ijse.vehiServePro.dto.tm.CustomerTm;
import lk.ijse.vehiServePro.dto.tm.EmployeeTm;
import lk.ijse.vehiServePro.model.CustomerModel;
import lk.ijse.vehiServePro.model.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public class EmployeeTableController {

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colUser;



    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }



    private void setCellValueFactory(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("user"));
    }
    private void loadAllCustomer(){
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try{
            List<EmployeeDTO> dtoList = model.getAllEmployee();

            for (EmployeeDTO dto : dtoList){
                obList.add(
                        new EmployeeTm(
                                dto.getName(),
                                dto.getId(),
                                dto.getContact(),
                                dto.getAddress(),
                                dto.getType(),
                                dto.getUser()
                        )
                );
            }
            tblEmployee.setItems(obList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
