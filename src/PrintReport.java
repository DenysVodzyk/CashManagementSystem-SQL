import entity.Customer;
import entity.Merchant;
import entity.Payment;
import reportService.CustomerReportService;
import reportService.MerchantReportService;
import repository.CustomerRepository;
import repository.MerchantRepository;
import repository.PaymentRepository;
import service.CustomerService;
import service.MerchantService;
import service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PrintReport {

    public void execute() {
        CustomerRepository customerRepository = new CustomerRepository();
        MerchantRepository merchantRepository = new MerchantRepository();
//        PaymentRepository paymentRepository = new PaymentRepository();

        for (Customer customer : customerRepository.getAll())
            System.out.println(customer);

        System.out.println("");

        System.out.println(customerRepository.getById(2));

        System.out.println("Merchant check");

        for (Merchant merchant : merchantRepository.getAll())
            System.out.println(merchant);

        System.out.println("");

        System.out.println(merchantRepository.getById(2));


//
//        List<Merchant> merchants = merchantRepository.getAll();
//        for (Merchant merchant : merchants)
//            System.out.println(merchant);
//
//        System.out.println("");
//
//        List<Payment> payments = paymentRepository.getAll();
//        for (Payment payment : payments)
//            System.out.println(payment);
//
//        System.out.println("merchant2 payments: !!! ");


//        for (Merchant merchant : merchants) {
//            if (merchant.getId() == 2) {
//                for (Payment payment : merchant.getPayments())
//                    System.out.println(payment);
//            }
//        }










       /* PaymentService paymentService = new PaymentService();
        MerchantReportService merchantReportService = new MerchantReportService();
        CustomerReportService customerReportService = new CustomerReportService();
        MerchantService merchantService = new MerchantService();


        PaymentRepository paymentRepository = new PaymentRepository();


        //Display all payment table
        for (Payment p : paymentService.getAll()) {
            System.out.println(p);
        }
        System.out.println("");

        //Total sum paid for a merchant - Clause 2
        Merchant merchant = merchantService.getById(2);
        String totalSum = merchantReportService.totalSumPaid(merchant);
        System.out.println(totalSum);
        System.out.println("");

// payments by merchant 2
        System.out.println("Payment by merchant: " + merchant.getId());
        MerchantRepository merchantRepository = new MerchantRepository();

        for (Payment payment : paymentRepository.getByMerchant(merchantRepository.getById(2)))
            System.out.println(payment);

        System.out.println("");
        System.out.println("Payments by merchant 2: ");
        paymentRepository.getAll();
        paymentRepository.getByMerchant(merchant);
        merchantRepository.getAll(paymentRepository);

//        for(Payment payment: merchant.getPayments())
//            System.out.println(payment);
//        System.out.println("");
//        System.out.println("");


//        for (Payment payment : merchant.getPayments()) {
//            System.out.println(payment);
//        }


        //List of merchants sorted alphabetically by name in descending order - Clause 3
        Set<String> sortedMerchants = merchantReportService.sortBasedOnOrder("desc");
        System.out.println(sortedMerchants);
        System.out.println("");

        //               add entry to payment table - Clause 4
//            PaymentService paymentService = new PaymentService();
//        LocalDateTime dt = LocalDateTime.of(2020, 12, 12, 00, 00, 00);
//        CustomerService customerService = new CustomerService();
//
//        Merchant merchant_3 = merchantService.getById(3);
//        Customer customer_3 = customerService.getById(3);
//        Payment newPayment = new Payment(dt, merchant_3, customer_3, "elliptical", 1000);
//        paymentService.addPayment(newPayment);

        //Display all merchants table
        for (Merchant m : merchantService.getAll(paymentRepository)) {
            System.out.println(m);
        }
        System.out.println("");

        //send funds to merchant - Clause 5
        merchantService.sendFunds(paymentRepository);


        //Display all merchants table
        for (Merchant m : merchantService.getAll(paymentRepository)) {
            System.out.println(m);
        }
        System.out.println("");


//        most active customer - Clause 7

//            Customer mostActive = customerReportService.getMostActiveCustomer(LocalDate.of(2012, 06, 22), LocalDate.of(2012, 07, 02));
//            System.out.println(mostActive);
*/
    }
}




/*
*         System.out.println("Customers: ");
        for (Customer customer : customerRepository.getAll())
            System.out.println(customer);

        System.out.println();

        System.out.println("Merchants: ");
        for (Merchant merchant : merchantRepository.getAll(paymentRepository))
            System.out.println(merchant);

        System.out.println();

        System.out.println("Payments: ");
        for (Payment payment : paymentRepository.getAll())
            System.out.println(payment);
        System.out.println();

        System.out.println("Payments by merchant 2: ");
        for (Payment payment : paymentRepository.getByMerchant(merchantRepository.getById(2)))
            System.out.println(payment);
        System.out.println();

        System.out.println("Payments by customer 2: ");
        for (Payment payment : paymentRepository.getByCustomer(customerRepository.getById(2)))
            System.out.println(payment);
        System.out.println();


        System.out.println("Payments by merchant2!!!: ");
        List<Payment> paymentToSet = paymentRepository.getByMerchant(merchantRepository.getById(2));
//        System.out.println(paymentToSet);
        Merchant merchant2 = merchantRepository.getById(2);

        merchant2.setPayments(paymentToSet);

        System.out.println(merchant2.getPayments());
*
* */