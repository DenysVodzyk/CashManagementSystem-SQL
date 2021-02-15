package repository;

import dBUtils.DBConnection;
import entity.Customer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public Customer getCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        String ccNo = rs.getString("ccNo");
        String ccType = rs.getString("ccType");
        Date maturityDate = rs.getDate("maturity");
        LocalDate maturity = maturityDate == null ? null : maturityDate.toLocalDate();

        return new Customer(id, name, address, email, ccNo, ccType, maturity);
    }

    public Customer getById(int id, boolean isPaymentKnown) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                customers.add(getCustomer(rs));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
