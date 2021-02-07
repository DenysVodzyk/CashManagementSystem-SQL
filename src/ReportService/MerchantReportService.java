package ReportService;

import Entity.Merchant;
import Entity.Payment;
import Service.MerchantService;
import Service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class MerchantReportService {
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();

    public MerchantReportService() throws IOException, SQLException {
        merchantService.readFromDB();
        paymentService.readFromDB();
    }

    public String totalSumPaid(int id) {
        Merchant merchant = merchantService.getById(id);
        int merchant_id = merchant.getId();
        String title = merchant.getName();
        LocalDate lastSent = merchant.getLastSent();
        double totalSum = 0;

        for (Payment payment : paymentService.getPayments()) {
            if (payment.getMerchantId() == id)
                totalSum += payment.getSumPaid();
        }
        return "Total sum paid: " + totalSum + ". Merchant Id: " + merchant_id + ", title: " + title
                + ", last sent: " + lastSent;
    }

    public ArrayList<String> sortedDescendingOrder() {
        ArrayList<String> merchantsByName = new ArrayList<>();
        for (Merchant merchant : merchantService.getMerchants()) {
            merchantsByName.add(merchant.getName());
        }
        Collections.sort(merchantsByName, Collections.reverseOrder());
        return merchantsByName;
    }

}
