package entity;

import java.time.LocalDate;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String address;
    private String email;
    private String ccNo;
    private String ccType;
    private LocalDate maturity;
    private List<Payment> payments;

    public Customer(String name, String address, String email, String ccNo, String ccType, LocalDate maturity) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.ccNo = ccNo;
        this.ccType = ccType;
        this.maturity = maturity;
    }

    public Customer(int id, String name, String address, String email, String ccNo, String ccType, LocalDate maturity) {
        this(name, address, email, ccNo, ccType, maturity);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public LocalDate getMaturity() {
        return maturity;
    }

    public void setMaturity(LocalDate maturity) {
        this.maturity = maturity;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", ccNo='" + ccNo + '\'' +
                ", ccType='" + ccType + '\'' +
                ", maturity=" + maturity +
                '}';
    }
}
