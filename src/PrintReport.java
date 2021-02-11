import Repository.PaymentRepository;
import Entity.Customer;
import Entity.Merchant;
import Entity.Payment;
import ReportService.CustomerReportService;
import ReportService.MerchantReportService;
import Service.CustomerService;
import Service.MerchantService;
import Service.PaymentService;

import java.time.LocalDateTime;
import java.util.Set;

public class PrintReport {

    public void execute() {

        PaymentService paymentService = new PaymentService();
        MerchantReportService merchantReportService = new MerchantReportService();
        CustomerReportService customerReportService = new CustomerReportService();
        MerchantService merchantService = new MerchantService();

        //Display all payment table
        for (Payment p : paymentService.getAll()) {
            System.out.println(p);
        }
        System.out.println("");

        //Total sum paid for a merchant - Clause 2
        String totalSum = merchantReportService.totalSumPaid(2);
        System.out.println(totalSum);
        System.out.println("");

        //List of merchants sorted alphabetically by name in descending order - Clause 3
        Set<String> sortedMerchants = merchantReportService.sortedDescendingOrder();
        System.out.println(sortedMerchants);
        System.out.println("");

        //               add entry to payment table - Clause 4
//            PaymentRepository paymentRepository = new PaymentRepository();
//            LocalDateTime dt = LocalDateTime.of(2020, 12, 12, 00, 00, 00);
//            MerchantService merchantService = new MerchantService();
//            CustomerService customerService = new CustomerService();
//
//            Merchant merchant_3 = merchantService.getById(3);
//            Customer customer_3 = customerService.getById(3);
//            Payment newPayment = new Payment(dt, merchant_3, customer_3, "elliptical", 1000);
//            paymentRepository.addPayment(newPayment);

        //Display all merchants table
        for (Merchant m : merchantService.getAll()) {
            System.out.println(m);
        }
        System.out.println("");

        //send funds to merchant - Clause 5
        merchantService.sendFunds();


        //Display all merchants table
        for (Merchant m : merchantService.getAll()) {
            System.out.println(m);
        }
        System.out.println("");






//        most active customer - Clause 7

//            Customer mostActive = customerReportService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
//            System.out.println(mostActive);

    }
}


