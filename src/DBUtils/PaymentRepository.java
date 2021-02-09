package DBUtils;

import Entity.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentRepository {

    //Lesson 7, clause 4

    public void addPayment(Payment payment) throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO payment(dt, merchantId, customerId, goods, sumPaid, chargePaid) VALUES (?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(sql);
        java.sql.Timestamp dt = java.sql.Timestamp.valueOf(payment.getDt());
        stm.setTimestamp(1, dt);
        stm.setInt(2, payment.getMerchantId());
        stm.setInt(3, payment.getCustomerId());
        stm.setString(4, payment.getGoods());
        stm.setDouble(5, payment.getSumPaid());
        stm.setDouble(6, payment.getChargePaid());
        stm.executeUpdate();
        stm.close();
    }
}
