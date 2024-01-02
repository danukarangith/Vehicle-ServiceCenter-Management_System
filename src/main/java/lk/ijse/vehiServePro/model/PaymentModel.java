package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.CustomerDTO;
import lk.ijse.vehiServePro.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {
    public boolean savePayment(final PaymentDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO payment VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAmount());
        pstm.setString(4, dto.getMethod());
        pstm.setString(5, dto.getDetail());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    public boolean updatePayment( PaymentDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "UPDATE payment set customer_name = ?,payment_amount = ?,payment_method = ? ,detail = ? WHERE payment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAmount());
        pstm.setString(3, dto.getMethod());
        pstm.setString(4, dto.getDetail());
        pstm.setString(5, dto.getId());


        return pstm.executeUpdate() > 0;
    }
    public boolean deletePayment(String id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM payment WHERE payment_id= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }
}
