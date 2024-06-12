package com.devstack.lms.entity;

import com.devstack.lms.utill.PaymentType;

import java.util.Date;

public class Registration {
    private String registrationId;
    private Date registeredDate;
    private byte[] nic;
    private PaymentType paymentType;
    private String student;
    private String course;

    public Registration(String s, String string, String s1, String string1) {
    }

    public Registration(String registrationId, Date registeredDate, byte[] nic, PaymentType paymentType, String student, String course) {
        this.registrationId = registrationId;
        this.registeredDate = registeredDate;
        this.nic = nic;
        this.paymentType = paymentType;
        this.student = student;
        this.course = course;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
