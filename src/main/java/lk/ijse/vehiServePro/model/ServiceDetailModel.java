package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;

import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.ReservationDTO;
import lk.ijse.vehiServePro.dto.ServiceDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailModel {
    public boolean saveDetail(final ServiceDetailDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO service_detail VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getCuname());
        pstm.setString(3, dto.getNumber());
        pstm.setString(4, dto.getDate());
        pstm.setString(5, dto.getTime());
        pstm.setString(6, dto.getDetail());
        pstm.setString(7, dto.getEname());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    public boolean updateDetail( ServiceDetailDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "UPDATE service_detail set customer_name = ?,vehicle_number = ?,service_date = ? ,service_time = ? ,details = ?,employee_name = ? WHERE service_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getCuname());
        pstm.setString(2, dto.getNumber());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getTime());
        pstm.setString(5, dto.getDetail());
        pstm.setString(6, dto.getEname());
        pstm.setString(7, dto.getId());


        return pstm.executeUpdate() > 0;
    }
    public boolean deleteDetail(String id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM service_detail WHERE service_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }



}
