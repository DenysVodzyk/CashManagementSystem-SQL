package ReportService;

import Entity.Merchant;
import Entity.Payment;
import Service.PaymentService;

import java.time.LocalDate;
import java.util.*;

public class MerchantReportService {
    PaymentService paymentService = new PaymentService();

    // Lesson 7 - clause 2
    public String totalSumPaid(Merchant merchant) {
        String title = "";
        LocalDate lastSent = null;
        double totalSum = 0;

        for (Payment payment : paymentService.getAll()) {
            if (payment.getMerchantId() == merchant.getId()) {
                title = payment.getMerchant().getName();
                lastSent = payment.getMerchant().getLastSent();
                totalSum += payment.getSumPaid();
            }
        }
        return "Total sum paid: " + totalSum + ". Merchant Id: " + merchant.getId() + ", title: " + title
                + ", last sent: " + lastSent;
    }

    //Lesson 7 - Clause 3
    public Set<String> sortBasedOnOrder(String sortingOrder) {
        Set<String> merchants = new TreeSet<String>();

        for (Payment payment : paymentService.getAll())
            merchants.add(payment.getMerchant().getName());

        if (sortingOrder.toLowerCase().contains("desc"))
            return ((TreeSet<String>) merchants).descendingSet();

        return merchants;
    }
}