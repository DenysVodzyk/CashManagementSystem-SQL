package service;

import repository.CustomerRepository;
import repository.MerchantRepository;
import repository.PaymentRepository;
import entity.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;
    private MerchantService merchantService;

    public PaymentService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();

        this.customerRepository.setPaymentRepository(paymentRepository);
        this.paymentRepository.setCustomerRepository(customerRepository);
        this.paymentRepository.setMerchantRepository(merchantRepository);
        this.merchantService = new MerchantService();
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

    public boolean addPayment(Payment payment) {
        payment.getMerchant().setNeedToSend(merchantService.calculateNeedToSend(payment));
        payment.setChargePaid(payment.getSumPaid() * payment.getMerchant().getCharge() / 100);
        paymentRepository.addPayment(payment);
        return true;
    }
}


//    private CustomerRepository cR = new CustomerRepository();
//    private MerchantRepository mR = new MerchantRepository();
//
//    private PaymentRepository paymentRepository = new PaymentRepository(cR, mR);