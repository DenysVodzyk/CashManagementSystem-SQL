import Entity.Customer;
import Entity.Merchant;
import ReportService.CustomerReportService;
import Entity.Payment;
import ReportService.MerchantReportService;
import Service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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


        // add entry to payment table - Clause 4
//        PaymentTableService paymentTableService = new PaymentTableService();
//        java.sql.Timestamp dt = java.sql.Timestamp.valueOf("2020-10-10 02:10:45");
//        paymentTableService.addPayment(dt, 3, 3, "rower", 2000, 50);


        //most active customer - Clause 7
        CustomerReportService customerTableService = new CustomerReportService();
        Customer mostActive = customerTableService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
        System.out.println(mostActive);


    }
}
