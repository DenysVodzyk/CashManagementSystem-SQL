package repository;

import dBUtils.DBConnection;
import entity.Customer;
import entity.Merchant;
import entity.Payment;
import service.CustomerService;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private CustomerRepository customerRepository = new CustomerRepository();
    private MerchantRepository merchantRepository = new MerchantRepository();


    public Payment getPayment(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Timestamp dtStamp = rs.getTimestamp("dt");
        LocalDateTime dt = dtStamp.toLocalDateTime();
        int merchantId = rs.getInt("merchantId");
        int customerId = rs.getInt("customerId");
        String goods = rs.getString("goods");
        double sumPaid = rs.getDouble("sumPaid");
        double chargePaid = rs.getDouble("chargePaid");

        return new Payment(id, dt, merchantRepository.getById(merchantId), customerRepository.getById(customerId), goods, sumPaid, chargePaid);
    }

    private List<Payment> getPaymentsBy(List<Payment> payments, String sql) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                payments.add(getPayment(rs));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        return getPaymentsBy(payments, sql);
    }

    public List<Payment> getByMerchant(Merchant merchant) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE merchantId=" + merchant.getId();
        return getPaymentsBy(payments, sql);
    }

    public List<Payment> getByCustomer(Customer customer) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE customerId=" + customer.getId();
        return getPaymentsBy(payments, sql);
    }

    //Lesson 7, clause 4
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payment(dt, merchantId, customerId, goods, sumPaid, chargePaid) VALUES (?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            java.sql.Timestamp dt = java.sql.Timestamp.valueOf(payment.getDt());
            stm.setTimestamp(1, dt);
            stm.setInt(2, payment.getMerchantId());
            stm.setInt(3, payment.getCustomerId());
            stm.setString(4, payment.getGoods());
            stm.setDouble(5, payment.getSumPaid());
            payment.setChargePaid(payment.getSumPaid() * payment.getMerchant().getCharge() / 100);
            stm.setDouble(6, payment.getChargePaid());
            merchantRepository.updateNeedToSend(payment);
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Payment> getAll() {
//        List<Payment> payments = new ArrayList<>();
//        String sql = "SELECT * FROM payment";
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement stm = con.prepareStatement(sql)) {
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                Timestamp dtStamp = rs.getTimestamp("dt");
//                LocalDateTime dt = dtStamp.toLocalDateTime();
//                int merchantId = rs.getInt("merchantId");
//                int customerId = rs.getInt("customerId");
//                String goods = rs.getString("goods");
//                double sumPaid = rs.getDouble("sumPaid");
//                double chargePaid = rs.getDouble("chargePaid");
//
//                payments.add(new Payment(id, dt, merchantService.getById(merchantId), customerService.getById(customerId), goods, sumPaid, chargePaid));
//            }
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//        return payments;
//    }


//    public List<Payment> getByMerchant(Merchant merchant) {
//        String sql = "SELECT * FROM payment WHERE ?";
//
//        List<Payment> payments = new ArrayList<>();
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement stm = con.prepareStatement(sql)) {
//            stm.setInt(1, merchant.getId());
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                Timestamp dtStamp = rs.getTimestamp("dt");
//                LocalDateTime dt = dtStamp.toLocalDateTime();
//                int merchantId = rs.getInt("merchantId");
//                int customerId = rs.getInt("customerId");
//                String goods = rs.getString("goods");
//                double sumPaid = rs.getDouble("sumPaid");
//                double chargePaid = rs.getDouble("chargePaid");
//
//                payments.add(new Payment(id, dt, merchant, customerService.getById(customerId), goods, sumPaid, chargePaid));
//            }
//        } catch (SQLException | IOException throwables) {
//            throwables.printStackTrace();
//        }
//        return payments;
//    }
}
