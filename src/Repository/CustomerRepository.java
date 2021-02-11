package DBUtils;

import Entity.Customer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * FROM customer";

    public List<Customer> getAll() {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String ccNo = rs.getString("ccNo");
                String ccType = rs.getString("ccType");
                Date maturityDate = rs.getDate("maturity");
                LocalDate maturity = maturityDate == null ? null : maturityDate.toLocalDate();

                customers.add(new Customer(id, name, address, email, ccNo, ccType, maturity));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
