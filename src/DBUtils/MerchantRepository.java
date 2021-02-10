package DBUtils;

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
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM merchant";
            stm = con.prepareStatement(sql);
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
        } finally {
            if (con != null) {
                try {
                    stm.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return merchants;
    }

    public void update(Payment payment) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.getConnection();
            String sql = "UPDATE merchant SET needToSend = ? WHERE id = ?";
            stm = con.prepareStatement(sql);
            double needToSend = payment.getMerchant().getNeedToSend() + (payment.getSumPaid() - payment.getMerchant().getCharge());
            stm.setDouble(1, needToSend);
            stm.setInt(2, payment.getMerchantId());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stm.executeUpdate();
                    stm.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

}
