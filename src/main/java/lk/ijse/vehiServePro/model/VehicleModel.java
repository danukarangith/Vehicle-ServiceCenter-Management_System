package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.VehicleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    public boolean saveVehicle(final VehicleDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getNum());
        pstm.setString(4, dto.getBrand());
        pstm.setString(5, dto.getType());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    public boolean updateVehicle( VehicleDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "UPDATE vehicle set customer_id = ?,veh_num = ?,veh_brand = ? ,veh_type = ? WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getNum());
        pstm.setString(3, dto.getBrand());
        pstm.setString(4, dto.getType());
        pstm.setString(5, dto.getId());


        return pstm.executeUpdate() > 0;
    }
    public boolean deleteCustomer(String id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM vehicle WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }
    public List<VehicleDTO> getAllVehicle() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<VehicleDTO> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new VehicleDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return cusList;
    }
}
