package Service;

import DBUtils.DBConnection;
import Entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private List<Payment> payments = new ArrayList<>();
    private CustomerService customerService = new CustomerService();
    private MerchantService merchantService = new MerchantService();

    public boolean readFromDB() throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM payment";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        customerService.readFromDB();
        merchantService.readFromDB();

        while (rs.next()) {
            int id = rs.getInt("id");
            Timestamp dtStamp = rs.getTimestamp("dt");
            LocalDateTime dt = dtStamp.toLocalDateTime();
            int merchantId = rs.getInt("merchantId");
            int customerId = rs.getInt("customerId");
            String goods = rs.getString("goods");
            double sumPaid = rs.getDouble("sumPaid");
            double chargePaid = rs.getDouble("chargePaid");

            payments.add(new Payment(id, dt, merchantService.getById(merchantId), customerService.getById(customerId), goods, sumPaid, chargePaid));
        }
        return !payments.isEmpty();
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Payment> getPaymentFromTimeInterval(LocalDate startDate, LocalDate endDate, List<Payment> payments) {
        List<Payment> paymentsFromTimeInterval = new ArrayList<>();
        for (Payment p : payments) {
            if (!p.getDt().toLocalDate().isBefore(startDate) && !p.getDt().toLocalDate().isAfter(endDate)) {
                paymentsFromTimeInterval.add(p);
            }
        }
        return paymentsFromTimeInterval;
    }
}

//    public static void main(String[] args) throws IOException, SQLException {
//        PaymentService paymentService = new PaymentService();
//        paymentService.readFromDB();
//        for (Payment p : paymentService.getPaymentFromTimeInterval(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02), paymentService.getPayments())) {
//            System.out.println(p);
//        }
//
//    }
// }