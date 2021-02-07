package Service;

import DBUtils.DBConnection;
import Entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private List<Payment> payments = new ArrayList<>();

    public boolean readFromDB() throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM payment";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            Timestamp dtStamp = rs.getTimestamp("dt");
            LocalDateTime dt = dtStamp.toLocalDateTime();
            int merchantId = rs.getInt("merchantId");
            int customerId = rs.getInt("customerId");
            String goods = rs.getString("goods");
            double sumPaid = rs.getDouble("sumPaid");
            double chargePaid = rs.getDouble("chargePaid");

            payments.add(new Payment(id, dt, merchantId, customerId, goods, sumPaid, chargePaid));
        }
        return !payments.isEmpty();
    }

    public List<Payment> getPayments() {
        return payments;
    }
}

//   public void readCustomerMerchantDB() {
//        try {
//            customerService.readFromDB();
//            merchantService.readFromDB();
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }