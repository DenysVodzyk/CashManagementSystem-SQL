package repository;

import dBUtils.DBConnection;
import entity.Merchant;
import entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantRepository {
//    PaymentRepository paymentRepository = new PaymentRepository();

    public Merchant getMerchant(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String bankName = rs.getString("bankName");
        String swift = rs.getString("swift");
        String account = rs.getString("account");
        double charge = rs.getDouble("charge");
        int period = rs.getInt("period");
        double minSum = rs.getDouble("minSum");
        double needToSend = rs.getDouble("needToSend");
        double sent = rs.getDouble("sent");
        Date lastSentDate = rs.getDate("lastSent");
        LocalDate lastSent = lastSentDate == null ? null : lastSentDate.toLocalDate();
        return new Merchant(id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent);
    }


    public Merchant getById(int id) {
        Merchant merchant = null;
        String sql = "SELECT * FROM merchant WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                merchant = getMerchant(rs);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return merchant;
    }

    public List<Merchant> getAll() {
        List<Merchant> merchants = new ArrayList<>();
        String sql = "SELECT * FROM merchant";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                merchants.add(getMerchant(rs));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return merchants;
    }

    //Lesson 7, clause 4
    public void updateNeedToSend(Payment payment) {
        String sql = "UPDATE merchant SET needToSend = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setDouble(1, payment.getMerchant().getNeedToSend());
            stm.setInt(2, payment.getMerchantId());
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateSendFunds(Merchant merchant) {
        String sql = "UPDATE merchant SET needToSend = ?, sent = ?, lastSent = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            double needToSend = merchant.getNeedToSend();
            double sent = merchant.getSent();
            Date lastSent = java.sql.Date.valueOf(merchant.getLastSent());
            int id = merchant.getId();
            stm.setDouble(1, needToSend);
            stm.setDouble(2, sent);
            stm.setDate(3, lastSent);
            stm.setInt(4, id);
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}


//                List<Payment> payments = paymentRepository.getByMerchant(merchant);
//                merchant.setPayments(payments);
// merchant.setPayments(paymentRepository.getByMerchant(merchant));