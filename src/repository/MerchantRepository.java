package repository;

import entity.Customer;
import utils.DBConnection;
import entity.Merchant;
import entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantRepository {
    PaymentRepository paymentRepository;

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Merchant getMerchant(ResultSet rs, boolean isPaymentKnown) throws SQLException {
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
        Merchant merchant = new Merchant(id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent);

        if (!isPaymentKnown) {
            List<Payment> payments = paymentRepository.getByMerchant(merchant);
            merchant.setPayments(payments);
        }
        return merchant;
    }

    public Merchant getById(int id, boolean isPaymentKnown) {
        Merchant merchant = null;
        String sql = "SELECT * FROM merchant WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                merchant = getMerchant(rs, isPaymentKnown);
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
                merchants.add(getMerchant(rs, false));
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
            stm.setInt(2, payment.getMerchant().getId());
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

    public void addMerchant(Merchant merchant) {
        String sql = "INSERT INTO merchant(name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, merchant.getName());
            stm.setString(2, merchant.getBankName());
            stm.setString(3, merchant.getSwift());
            stm.setString(4, merchant.getAccount());
            stm.setDouble(5, merchant.getCharge());
            stm.setInt(6, merchant.getPeriod());
            stm.setDouble(7, merchant.getMinSum());
            stm.setDouble(8, merchant.getNeedToSend());
            stm.setDouble(9, merchant.getSent());
            java.sql.Date sent = null;
            if (merchant.getLastSent() != null) {
                sent = java.sql.Date.valueOf(merchant.getLastSent());
            }
            stm.setDate(10, sent);
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
