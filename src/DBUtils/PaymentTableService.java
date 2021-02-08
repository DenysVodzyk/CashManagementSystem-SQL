package DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentTableService {

    //Lesson 7, clause 4
    public void addPayment(java.sql.Timestamp dt, int merchantId, int customerId, String goods, double sumPaid, double chargePaid) throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO payment(dt, merchantId, customerId, goods, sumPaid, chargePaid) VALUES (?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setTimestamp(1, dt);
        stm.setInt(2, merchantId);
        stm.setInt(3, customerId);
        stm.setString(4, goods);
        stm.setDouble(5, sumPaid);
        stm.setDouble(6, chargePaid);
        stm.executeUpdate();
        stm.close();
    }
}
