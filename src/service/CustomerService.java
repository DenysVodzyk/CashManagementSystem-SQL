package service;

import repository.CustomerRepository;
import entity.Customer;
import repository.MerchantRepository;
import repository.PaymentRepository;
import utils.FileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;
    private FileReader fileReader;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();
        this.fileReader = new FileReader();

        this.customerRepository.setPaymentRepository(paymentRepository);
        this.paymentRepository.setCustomerRepository(customerRepository);
        this.paymentRepository.setMerchantRepository(merchantRepository);
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getById(int id) {
        return customerRepository.getById(id, false);
    }

    public List<Customer> getCustomersFromFile(String filePath) {
        List<String> data = fileReader.readFromFile(filePath);
        List<Customer> customers = new ArrayList<>();

        for (String customer : data) {
            String[] customerData = customer.split(",");
            String name = customerData[0];
            String address = customerData[1];
            String email = customerData[2];
            String ccNo = customerData[3];
            String ccType = customerData[4];
            LocalDate maturity = setMaturityDate(customerData[5]);

            customers.add(new Customer(name, address, email, ccNo, ccType, maturity));
        }
        return customers;
    }

    private LocalDate setMaturityDate(String maturityDate) {
        if (maturityDate != null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(maturityDate, formatter);
        return date;
    }
}
