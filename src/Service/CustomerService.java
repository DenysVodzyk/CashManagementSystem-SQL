package Service;

import DBUtils.DBConnection;
import Entity.Customer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public boolean readFromDB() throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

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
        con.close();
        return !customers.isEmpty();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getById(int id) {
        Customer customerToReturn = null;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customerToReturn = customer;
            }
        }
        return customerToReturn;
    }

}
