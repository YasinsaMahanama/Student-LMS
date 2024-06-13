package com.devstack.lms.entity;

import com.devstack.lms.utill.PaymentType;

import java.util.Date;

public class AllRegistration {
    private Date date;
    private String studentName;
    private PaymentType paymentType;

    public AllRegistration(Object object, String string, Object resultSetObject) {
    }

    public AllRegistration(Date date, String studentName, PaymentType paymentType) {
        this.date = date;
        this.studentName = studentName;
        this.paymentType = paymentType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
