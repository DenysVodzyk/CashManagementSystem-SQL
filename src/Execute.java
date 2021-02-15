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
import java.util.Set;

public class Execute {

    public void execute() {
        MerchantService merchantService = new MerchantService();
        CustomerService customerService = new CustomerService();
        PaymentService paymentService = new PaymentService();
        MerchantReportService merchantReportService = new MerchantReportService();
        CustomerReportService customerReportService = new CustomerReportService();

        //Display all payment table
        System.out.println("All payment table: ");
        for (Payment p : paymentService.getAll()) {
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
        for (Payment p : paymentService.getAll()) {
            System.out.println(p);
        }
        System.out.println("");

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

        //most active customer - Clause 7

        Customer mostActive = customerReportService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
        System.out.println("Most active customer: " + mostActive);

    }
}


//2021-02-15

/*  CustomerRepository cR = new CustomerRepository();
        MerchantRepository mR = new MerchantRepository();

        PaymentRepository paymentRepository = new PaymentRepository(cR, mR);
        MerchantRepository merchantRepository = new MerchantRepository(paymentRepository);
        CustomerRepository customerRepository = new CustomerRepository(paymentRepository);


        System.out.println("Customers:");
        for (Customer customer : customerRepository.getAll())
            System.out.println(customer);

        System.out.println("");

        System.out.println(customerRepository.getById(2, false));
        System.out.println();
        for(Payment payment: customerRepository.getById(2, false).getPayments())
            System.out.println(payment);


        System.out.println("Merchants: ");

        for (Merchant merchant : merchantRepository.getAll())
            System.out.println(merchant);

        System.out.println("Payments: ");
        List<Payment> payments = paymentRepository.getAll();
        for (Payment payment : payments)
            System.out.println(payment);


        System.out.println(merchantRepository.getById(2, false));
        System.out.println("");


        List<Merchant> merchants = merchantRepository.getAll();
        for (Merchant merchant : merchants)
            System.out.println(merchant);

        System.out.println("");


        System.out.println(merchantRepository.getById(2, false).getPayments());*/
//
//for (Payment payment : paymentService.getAll()) {
//        System.out.println(payment);
//        }
//        System.out.println();
//
//        for (Merchant merchant : merchantService.getAll()) {
//        System.out.println(merchant);
//        }
//        System.out.println(merchantService.getById(2));
//        System.out.println(merchantService.getById(2).getPayments());
//        System.out.println();
//
//        for (Customer customer : customerService.getAll()) {
//        System.out.println(customer);
//        }
//        System.out.println(customerService.getById(2));
//        System.out.println(customerService.getById(2).getPayments());
//        System.out.println();
