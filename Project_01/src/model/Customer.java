/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author hanly
 */
public class Customer {
    private String customerCode;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    
    public Customer(String customerCode, String name, String phoneNumber, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "customer{" + "customerCode=" + customerCode + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.customerCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return Objects.equals(this.customerCode, other.customerCode);
    }
    
    public void display(){
        System.out.printf("%-18s:%s\n","Customer code",this.getCustomerCode());
        System.out.printf("%-18s:%s\n","Customer name",this.getName());
        System.out.printf("%-18s:%s\n","Phone number",this.getPhoneNumber());
        System.out.printf("%-18s:%s\n","Email",this.getEmail());
    }
    
}
