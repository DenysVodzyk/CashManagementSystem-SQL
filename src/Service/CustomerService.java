package Service;

import DBUtils.CustomerRepository;
import Entity.Customer;

import java.util.List;


public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getById(int id) {
        Customer customerById = null;
        for (Customer customer : getAll()) {
            if (customer.getId() == id) {
                customerById = customer;
            }
        }
        return customerById;
    }

}
