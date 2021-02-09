package Entity;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private LocalDateTime dt;
    private int merchantId;
    private int customerId;
    private String goods;
    private double sumPaid;
    private double chargePaid;
    private Merchant merchant;
    private Customer customer;

    public Payment(LocalDateTime dt, Merchant merchant, Customer customer, String goods, double sumPaid, double chargePaid) {
        this.dt = dt;
        this.merchantId = merchant.getId();
        this.customerId = customer.getId();
        this.goods = goods;
        this.sumPaid = sumPaid;
        this.chargePaid = chargePaid;
        this.customer = customer;
        this.merchant = merchant;
    }

    public Payment(int id, LocalDateTime dt, Merchant merchant, Customer customer, String goods, double sumPaid, double chargePaid) {
        this(dt, merchant, customer, goods, sumPaid, chargePaid);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Merchant merchant) {
        this.merchantId = merchant.getId();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customer) {
        this.customerId = customer.getId();
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public double getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(double sumPaid) {
        this.sumPaid = sumPaid;
    }

    public double getChargePaid() {
        return chargePaid;
    }

    public void setChargePaid(double chargePaid) {
        this.chargePaid = chargePaid;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", dt=" + dt +
                ", merchantId=" + merchantId +
                ", customerId=" + customerId +
                ", goods='" + goods + '\'' +
                ", sumPaid=" + sumPaid +
                ", chargePaid=" + chargePaid +
                ", merchant=" + merchant +
                ", customer=" + customer +
                '}';
    }
}
