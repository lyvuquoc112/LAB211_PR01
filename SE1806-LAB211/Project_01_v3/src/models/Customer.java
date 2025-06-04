
package models;

import java.util.Objects;



public class Customer {

    private String customerCode;
    private String name;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(String customerCode, String name, String phone, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerCode=" + customerCode + ", name=" + name + ", phone=" + phone + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.customerCode);
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

    public void display() {
        System.out.format("%-18s:%s\n", "Customer code", this.getCustomerCode());
        System.out.format("%-18s:%s\n", "Customer name", this.getName());
        System.out.format("%-18s:%s\n", "Phone number", this.getPhone());
        System.out.format("%-18s:%s\n", "Email", this.getEmail());
    }
}
