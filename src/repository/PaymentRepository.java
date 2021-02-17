package repository;

import dBUtils.DBConnection;
import entity.Customer;
import entity.Merchant;
import entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setMerchantRepository(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Payment getPayment(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Timestamp dtStamp = rs.getTimestamp("dt");
        LocalDateTime dt = dtStamp.toLocalDateTime();
        int merchantId = rs.getInt("merchantId");
        int customerId = rs.getInt("customerId");
        String goods = rs.getString("goods");
        double sumPaid = rs.getDouble("sumPaid");
        double chargePaid = rs.getDouble("chargePaid");

        return new Payment(id,
                dt,
                merchantRepository.getById(merchantId, true),
                customerRepository.getById(customerId, true),
                goods,
                sumPaid,
                chargePaid);
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
            stm.setInt(2, payment.getMerchant().getId());
            stm.setInt(3, payment.getCustomer().getId());
            stm.setString(4, payment.getGoods());
            stm.setDouble(5, payment.getSumPaid());
            stm.setDouble(6, payment.getChargePaid());
            merchantRepository.updateNeedToSend(payment);
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}