package lk.ijse.vehiServePro.model;

import lk.ijse.vehiServePro.db.DbConnection;
import lk.ijse.vehiServePro.dto.StockManageDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class StockManageModel {
   /* private final UserModel userModel = new UserModel();

    public boolean placeOrder(StockManageDTO pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = UserModel.saveUser(pDto.getUserName(), pDto.getSupplierId());
            if (isOrderSaved) {
                boolean isUpdated = StockModel.updateStock(pDto.getTmList());
                if(isUpdated) {
                    boolean isUserSaved = UserModel.saveUser(pDto.getUserName(), pDto.getTmList());
                    if(isUserSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                // connection.close();
            }
        }
        return result;
    }
}*/
}
