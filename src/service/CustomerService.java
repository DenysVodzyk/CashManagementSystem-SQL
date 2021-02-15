package service;

import repository.CustomerRepository;
import entity.Customer;
import repository.MerchantRepository;
import repository.PaymentRepository;

import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();

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

}

//
//    private PaymentRepository paymentRepository = new PaymentRepository(cR, mR);
//    private CustomerRepository customerRepository = new CustomerRepository(paymentRepository);
