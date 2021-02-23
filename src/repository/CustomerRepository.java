package repository;

import utils.DBConnection;
import entity.Customer;
import entity.Payment;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    PaymentRepository paymentRepository;

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Customer getCustomer(ResultSet rs, boolean isPaymentKnown) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        String ccNo = rs.getString("ccNo");
        String ccType = rs.getString("ccType");
        Date maturityDate = rs.getDate("maturity");
        LocalDate maturity = maturityDate == null ? null : maturityDate.toLocalDate();
        Customer customer = new Customer(id, name, address, email, ccNo, ccType, maturity);
        if (!isPaymentKnown) {
            List<Payment> payments = paymentRepository.getByCustomer(customer);
            customer.setPayments(payments);
        }
        return customer;
    }

    public Customer getById(int id, boolean isPaymentKnown) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs, isPaymentKnown);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer getByName(String name, boolean isPaymentKnown) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs, isPaymentKnown);
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
                customers.add(getCustomer(rs, false));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer(name, address, email, ccNo, ccType, maturity) VALUES (?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, customer.getName());
            stm.setString(2, customer.getAddress());
            stm.setString(3, customer.getEmail());
            stm.setString(4, customer.getCcNo());
            stm.setString(5, customer.getCcType());
            java.sql.Date date = java.sql.Date.valueOf(customer.getMaturity());
            stm.setDate(6, date);
            stm.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
