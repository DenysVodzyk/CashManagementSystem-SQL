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

    public Payment(int id, LocalDateTime dt, int merchantId, int customerId, String goods, double sumPaid, double chargePaid) {
        this.id = id;
        this.dt = dt;
        this.merchantId = merchantId;
        this.customerId = customerId;
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
