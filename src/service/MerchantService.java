package service;

import entity.Payment;
import repository.CustomerRepository;
import repository.MerchantRepository;
import entity.Merchant;
import repository.PaymentRepository;
import utils.FileReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantService {
    private CustomerRepository customerRepository;
    private MerchantRepository merchantRepository;
    private PaymentRepository paymentRepository;

    public MerchantService() {
        this.customerRepository = new CustomerRepository();
        this.merchantRepository = new MerchantRepository();
        this.paymentRepository = new PaymentRepository();

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

    public List<Merchant> getMerchantFromFile(String filePath) {
        List<String> data = FileReader.readFromFile(filePath);
        List<Merchant> merchants = new ArrayList<>();

        for (String merchant : data) {
            String[] merchantData = merchant.split(",");
            String name = merchantData[0];
            String bankName = merchantData[1];
            String swift = merchantData[2];
            String account = merchantData[3];
            double charge = Double.parseDouble(merchantData[4]);
            int period = Integer.parseInt(merchantData[5]);
            double minSum = Double.parseDouble(merchantData[6]);
            double needToSend = Double.parseDouble(merchantData[7]);
            double sent = Double.parseDouble(merchantData[8]);
            LocalDate lastSent = null;
            if (!merchantData[9].equals("null")) {
                lastSent = FileReader.setLocalDate(merchantData[9], "yyyy/MM/dd");
            }

            merchants.add(new Merchant(name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent));
        }
        return merchants;
    }

    public void addMerchant(String filePath) {
        List<Merchant> merchantsFromFile = getMerchantFromFile(filePath);

        for (Merchant merchant : merchantsFromFile) {
            if (!getAll().contains(merchant)) {
                merchantRepository.addMerchant(merchant);
            }
        }
    }

}

