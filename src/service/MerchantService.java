package service;

import entity.Payment;
import repository.CustomerRepository;
import repository.MerchantRepository;
import entity.Merchant;
import repository.PaymentRepository;

import java.time.LocalDate;
import java.util.List;

public class MerchantService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;

    public MerchantService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();

        this.customerRepository.setPaymentRepository(paymentRepository);
        this.paymentRepository.setCustomerRepository(customerRepository);
        this.paymentRepository.setMerchantRepository(merchantRepository);
        this.merchantRepository.setPaymentRepository(paymentRepository);
    }

    public List<Merchant> getAll() {
        return merchantRepository.getAll();
    }

    public Merchant getById(int id) {
        return merchantRepository.getById(id, false);
    }

    public double calculateNeedToSend(Payment payment) {
        return payment.getMerchant().getNeedToSend() + (payment.getSumPaid() - payment.getMerchant().getCharge());
    }

    //Lesson 7, clause 5
    public void sendFunds() {
        for (Merchant merchant : getAll()) {
            if (merchant.getNeedToSend() >= merchant.getMinSum()) {
                merchant.setLastSent(LocalDate.now());
                double total = merchant.getSent() + merchant.getNeedToSend();
                merchant.setSent(total);
                merchant.setNeedToSend(0);
                merchantRepository.updateSendFunds(merchant);
            }
        }
    }


}

//    private CustomerRepository cR = new CustomerRepository();
//    private MerchantRepository mR = new MerchantRepository();
//
//    private PaymentRepository paymentRepository = new PaymentRepository(cR, mR);
//    private MerchantRepository merchantRepository = new MerchantRepository(paymentRepository);