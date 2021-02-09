import DBUtils.PaymentRepository;
import Entity.Customer;
import Entity.Merchant;
import ReportService.CustomerReportService;
import Entity.Payment;
import ReportService.MerchantReportService;
import Service.CustomerService;
import Service.MerchantService;
import Service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        //Display all payment table
        PaymentService paymentService = new PaymentService();
        paymentService.readFromDB();
        for (Payment p : paymentService.getPayments()) {
            System.out.println(p);
        }
        System.out.println("");

        //Total sum paid for a merchant - Clause 2
        MerchantReportService merchantReportService = new MerchantReportService();
        String totalSum = merchantReportService.totalSumPaid(2);
        System.out.println(totalSum);
        System.out.println("");

        //List of merchants sorted alphabetically by name in descending order - Clause 3
        TreeSet<String> sortedMerchants = merchantReportService.sortedDescendingOrder();
        System.out.println(sortedMerchants);
        System.out.println("");


        //     add entry to payment table - Clause 4
//        PaymentRepository paymentRepository = new PaymentRepository();
//        LocalDateTime dt = LocalDateTime.of(2020, 12, 12, 00, 00, 00);
//        MerchantService merchantService = new MerchantService();
//        CustomerService customerService = new CustomerService();
//        merchantService.readFromDB();
//        customerService.readFromDB();
//
//        Merchant merchant_3 = merchantService.getById(3);
//        Customer customer_3 = customerService.getById(3);
//        Payment newPayment = new Payment(dt, merchant_3, customer_3, "elliptical", 1500, 27);
//        paymentRepository.addPayment(newPayment);


        //most active customer - Clause 7
        CustomerReportService customerTableService = new CustomerReportService();
        Customer mostActive = customerTableService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
        System.out.println(mostActive);


    }
}
