package Repository;

import DBUtils.DBConnection;
import Entity.Merchant;
import Entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantRepository {

    public List<Merchant> getAll() {
        List<Merchant> merchants = new ArrayList<>();
        String sql = "SELECT * FROM merchant";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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

                merchants.add(new Merchant(id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent));
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
            double needToSend = payment.getMerchant().getNeedToSend() + (payment.getSumPaid() - payment.getMerchant().getCharge());
            stm.setDouble(1, needToSend);
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
