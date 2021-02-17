package reportService;

import entity.Merchant;
import entity.Payment;
import service.MerchantService;

import java.time.LocalDate;
import java.util.*;

public class MerchantReportService {
    MerchantService merchantService = new MerchantService();

    // Lesson 7 - clause 2
    public String totalSumPaid(Merchant merchant) {
        String title = "";
        LocalDate lastSent = null;
        double totalSum = 0;

        for (Payment payment : merchant.getPayments()) {
            title = merchant.getName();
            lastSent = merchant.getLastSent();
            totalSum += payment.getSumPaid();
        }
        return "Total sum paid: " + totalSum + ". Merchant Id: " + merchant.getId() + ", title: " + title
                + ", last sent: " + lastSent;
    }

    //Lesson 7 - Clause 3
    public Set<String> sortBasedOnOrder(String sortingOrder) {
        Set<String> merchants = new TreeSet<>();

        merchantService.getAll().forEach(merchant -> {
            merchants.add(merchant.getName());
        });

        if (sortingOrder.toLowerCase().contains("desc")) {
            return ((TreeSet<String>) merchants).descendingSet();
        }
        return merchants;
    }
}