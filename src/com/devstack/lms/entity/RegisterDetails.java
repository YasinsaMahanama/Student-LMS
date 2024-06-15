package com.devstack.lms.entity;

import com.devstack.lms.utill.PaymentType;

import java.util.Date;

public class RegisterDetails {
    private String studentId;
    private String studentName;
    private String address;
    private String Email;
    private int age;
    private String customerId;
    private String customerName;
    private double courseFee;
    private String registerId;
    private Date registeredDate;
    private byte[] nic;
    private PaymentType paymentType;

    public RegisterDetails() {
    }

    public RegisterDetails(String studentId, String studentName, String address, String email, int age, String customerId, String customerName, double courseFee, String registerId, Date registeredDate, byte[] nic, PaymentType paymentType) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        Email = email;
        this.age = age;
        this.customerId = customerId;
        this.customerName = customerName;
        this.courseFee = courseFee;
        this.registerId = registerId;
        this.registeredDate = registeredDate;
        this.nic = nic;
        this.paymentType = paymentType;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public byte[] getNic() {
        return nic;
    }

    public void setNic(byte[] nic) {
        this.nic = nic;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
