package ReportService;

import Entity.Customer;
import Entity.Payment;
import Service.CustomerService;
import Service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerReportService {
    PaymentService paymentService = new PaymentService();
    CustomerService customerService = new CustomerService();

    public Customer getMostActiveCustomer(LocalDate startDate, LocalDate endDate) throws IOException, SQLException {
        customerService.getAll();
        int id = mostActiveCustomerId(startDate, endDate);
        return customerService.getById(id);
    }

    public int mostActiveCustomerId(LocalDate startDate, LocalDate endDate) throws IOException, SQLException {
        paymentService.getAll();
        List<Integer> customersId = new ArrayList<>();
        List<Payment> paymentsFromInterval = paymentService.getPaymentFromTimeInterval(startDate, endDate);

        for (Payment payment : paymentsFromInterval) {
            customersId.add(payment.getCustomerId());
        }
        return mostCommonElement(customersId);
    }

    public Map<Integer, Integer> itemIntoMap(List<Integer> items) {
        Map<Integer, Integer> itemMap = new HashMap<>();
        for (int i : items) {
            Integer count = itemMap.get(i);
            itemMap.put(i, count != null ? count + 1 : 1);
        }
        return itemMap;
    }

    public int mostCommonElement(List<Integer> items) {
        Map<Integer, Integer> itemMap = itemIntoMap(items);
        int mostCommonKey = 0;
        int maxValue = -1;
        for (Map.Entry<Integer, Integer> entry : itemMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                mostCommonKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        return mostCommonKey;
    }

}
