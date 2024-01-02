package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public boolean saveEmployee(final EmployeeDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getId());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getType());
        pstm.setString(6, dto.getUser());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    public boolean updateEmployee( EmployeeDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "UPDATE employee set employee_id = ?,emp_address = ? ,emp_contact_number=?,emp_type=?,user_name = ? WHERE emp_name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getContact());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getType());
        pstm.setString(5, dto.getUser());
        pstm.setString(6, dto.getName());



        return pstm.executeUpdate() > 0;
    }
    public boolean deleteCustomer(String id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE emp_name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }

    public List<EmployeeDTO> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<EmployeeDTO> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return cusList;
    }


}
