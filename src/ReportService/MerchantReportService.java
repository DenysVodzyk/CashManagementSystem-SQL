package ReportService;

import Entity.Payment;
import Service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MerchantReportService {
    PaymentService paymentService = new PaymentService();

    public MerchantReportService() throws IOException, SQLException {
        paymentService.readFromDB();
    }

    public String totalSumPaid(int id) {
        String title = "";
        LocalDate lastSent = null;
        double totalSum = 0;

        for (Payment payment : paymentService.getPayments()) {
            if (payment.getMerchantId() == id) {
                title = payment.getMerchant().getName();
                lastSent = payment.getMerchant().getLastSent();
                totalSum += payment.getSumPaid();
            }
        }
        return "Total sum paid: " + totalSum + ". Merchant Id: " + id + ", title: " + title
                + ", last sent: " + lastSent;
    }

    public TreeSet<String> sortedDescendingOrder() {
        TreeSet<String> merchantsByName = new TreeSet<>();
        for (Payment payment : paymentService.getPayments())
            merchantsByName.add(payment.getMerchant().getName());
        TreeSet<String> merchantsByNameDescend = (TreeSet<String>) merchantsByName.descendingSet();
        return merchantsByNameDescend;
    }
}
