package com.apofig.multith.connectionpool.dom;

import java.util.Date;

public class Person {
    public static final Person NULL = new Person("", "", 0);

    private String name;
    private String phoneNumber;
    private Date date;

    public Person(String name, String phoneNumber, long date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = new Date(date);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName(int length) {
        if (name.length() > length) {
            return name.substring(0, length);
        }
        return name;
    }
}
