package service;

import entity.Customer;
import entity.Merchant;
import repository.CustomerRepository;
import repository.MerchantRepository;
import repository.PaymentRepository;
import entity.Payment;
import utils.FileReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;
    private MerchantService merchantService;
    private CustomerService customerService;

    public PaymentService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();

        this.paymentRepository.setCustomerRepository(customerRepository);
        this.paymentRepository.setMerchantRepository(merchantRepository);
        this.merchantService = new MerchantService();
        this.customerService = new CustomerService();
    }

    public List<Payment> getAll() {
        return paymentRepository.getAll();
    }

    public List<Payment> getPaymentFromTimeInterval(LocalDate startDate, LocalDate endDate) {
        List<Payment> paymentsFromTimeInterval = new ArrayList<>();
        for (Payment p : getAll()) {
            if (!p.getDt().toLocalDate().isBefore(startDate) && !p.getDt().toLocalDate().isAfter(endDate)) {
                paymentsFromTimeInterval.add(p);
            }
        }
        return paymentsFromTimeInterval;
    }

    public List<Payment> getPaymentFromFile(String filePath) {
        List<String> data = FileReader.readFromFile(filePath);
        List<Payment> payments = new ArrayList<>();

        for (String payment : data) {
            String[] paymentData = payment.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dt = LocalDateTime.parse(paymentData[0], formatter);
            Customer customer = customerService.getByName(paymentData[1]);
            Merchant merchant = merchantService.getByName(paymentData[2]);
            String goods = paymentData[3];
            double sumPaid = Double.parseDouble(paymentData[4]);

            payments.add(new Payment(dt, merchant, customer, goods, sumPaid));
        }
        return payments;
    }

    public boolean addPayment(Payment payment) {
        payment.getMerchant().setNeedToSend(merchantService.calculateNeedToSend(payment));
        payment.setChargePaid(payment.getSumPaid() * payment.getMerchant().getCharge() / 100);
        paymentRepository.addPayment(payment);
        return true;
    }

    public void addPaymentFromFile(String filePath) {
        List<Payment> paymentsFromFile = getPaymentFromFile(filePath);

        for (Payment payment : paymentsFromFile) {
            addPayment(payment);
        }
    }


}