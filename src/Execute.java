import entity.Customer;
import entity.Merchant;
import entity.Payment;
import reportService.CustomerReportService;
import reportService.MerchantReportService;
import service.CustomerService;
import service.MerchantService;
import service.PaymentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Execute {

    public void execute() {
        MerchantService merchantService = new MerchantService();
        CustomerService customerService = new CustomerService();
        PaymentService paymentService = new PaymentService();
        MerchantReportService merchantReportService = new MerchantReportService();
        CustomerReportService customerReportService = new CustomerReportService();

        //Collections:
        List<Payment> payments = paymentService.getAll();
        List<Merchant> merchants = merchantService.getAll();
        List<Customer> customers = customerService.getAll();


        //Display all payment table
        System.out.println("All payment table: ");
        for (Payment p : payments) {
            System.out.println(p);
        }
        System.out.println("");

        //Total sum paid for a merchant2 - Clause 2
        Merchant merchant2 = merchantService.getById(2);
        System.out.println("Merchant 2: " + merchant2);
        String totalSum = merchantReportService.totalSumPaid(merchant2);
        System.out.println("Total sum paid: " + totalSum);
        System.out.println("");

        // payments by merchant 2
        System.out.println("Payments by merchant: " + merchant2.getId());

        for (Payment payment : merchant2.getPayments()) {
            System.out.println(payment);
        }
        System.out.println("");

        //List of merchants sorted alphabetically by name in descending order - Clause 3
        System.out.println("Merchants sorted in descending order: ");
        Set<String> sortedMerchants = merchantReportService.sortBasedOnOrder("desc");
        System.out.println(sortedMerchants);
        System.out.println("");

        //add entry to payment table - Clause 4
        LocalDateTime dt = LocalDateTime.of(2020, 12, 12, 00, 00, 00);

//        Merchant merchant_3 = merchantService.getById(3);
//        Customer customer_3 = customerService.getById(3);
//        Payment newPayment = new Payment(dt, merchant_3, customer_3, "elliptical3", 1100);
//        paymentService.addPayment(newPayment);

        //Display all payment table
        System.out.println("All payment table: ");
        for (Payment p : payments) {
            System.out.println(p);
        }
        System.out.println("");

        //Display all merchants table
        for (Merchant m : merchants) {
            System.out.println(m);
        }
        System.out.println("");

        //send funds to merchant - Clause 5
        merchantService.sendFunds();


        //Display all merchants table
        for (Merchant m : merchants) {
            System.out.println(m);
        }
        System.out.println("");

        //most active customer - Clause 7

        Customer mostActive = customerReportService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
        System.out.println("Most active customer: " + mostActive);

        //Add customers from file - Clause 8
        //Add customers from file:
        String customersFilePath = "src/filesToLoad/CustomersData.csv";
        customerService.addCustomer(customersFilePath);

        //Add merchants from file:
        String merchantsFilePath = "src/filesToLoad/MerchantsData.csv";
        merchantService.addMerchant(merchantsFilePath);

    }
}
