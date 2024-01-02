package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static Object password;

    public boolean saveCustomer(UserDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO admin VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getEmail());
        pstm.setString(3,dto.getPassword());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }



    public static boolean isExistUser(String userName, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT user_pas ,user_name  FROM admin WHERE user_name=? AND user_pas=?");
        statement.setObject(1, userName);
        statement.setObject(2, password);
        ResultSet resultSet = statement.executeQuery();
        String dbUserName = null;
        String dbPassword = null;
        if (resultSet.next()) {
            dbPassword = resultSet.getString(1);
            dbUserName = resultSet.getString(2);
            //DbConnection.email = resultSet.getString(3);
        }
        return userName.equals(dbUserName) && password.equals(dbPassword);
    }



}