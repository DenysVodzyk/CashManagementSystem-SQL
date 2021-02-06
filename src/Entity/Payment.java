package Entity;

import java.time.LocalDate;

public class Payment {
    private int id;
    private LocalDate dt;
    private int merchantId;
    private int customerId;
    private String goods;
    private double sumPaid;
    private double chargePaid;

    public Payment(int id, LocalDate dt, Merchant merchant, Customer customer, String goods, double sumPaid, double chargePaid) {
        this.id = id;
        this.dt = dt;
        this.merchantId = merchant.getId();
        this.customerId = customer.getId();
        this.goods = goods;
        this.sumPaid = sumPaid;
        this.chargePaid = chargePaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
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
                '}';
    }
}
