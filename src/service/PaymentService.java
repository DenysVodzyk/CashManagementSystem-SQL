package Service;

import Repository.PaymentRepository;
import Entity.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private PaymentRepository paymentRepository = new PaymentRepository();

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
}
