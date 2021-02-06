package Service;

import DBUtils.DBConnection;
import Entity.Merchant;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantService {
    private List<Merchant> merchants = new ArrayList<>();

    public boolean readFromDB() throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM merchant";
        PreparedStatement stm = con.prepareStatement(sql);
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
        return !merchants.isEmpty();
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

}
