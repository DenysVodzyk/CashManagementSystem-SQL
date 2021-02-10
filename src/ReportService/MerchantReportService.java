package ReportService;

import Entity.Payment;
import Service.PaymentService;

import java.time.LocalDate;
import java.util.*;

public class MerchantReportService {
    PaymentService paymentService = new PaymentService();

    // Lesson 7 - clause 2
    public String totalSumPaid(int id) {
        String title = "";
        LocalDate lastSent = null;
        double totalSum = 0;

        for (Payment payment : paymentService.getAll()) {
            if (payment.getMerchantId() == id) {
                title = payment.getMerchant().getName();
                lastSent = payment.getMerchant().getLastSent();
                totalSum += payment.getSumPaid();
            }
        }
        return "Total sum paid: " + totalSum + ". Merchant Id: " + id + ", title: " + title
                + ", last sent: " + lastSent;
    }

    //Lesson 7 - Clause 3
    public Set<String> sortedDescendingOrder() {
        Set<String> merchantsByName = new TreeSet<>();
        for (Payment payment : paymentService.getAll())
            merchantsByName.add(payment.getMerchant().getName());
        Set<String> merchantsByNameDescend = ((TreeSet<String>) merchantsByName).descendingSet();
        return merchantsByNameDescend;
    }
}
