/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import business.Customers;
import business.SetMenus;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import model.Customer;
import model.SetMenu;

/**
 *
 * @author hanly
 */
public class Order implements Serializable{
    private String orderId;
    private String customerCode;
    private String setMenuCode;
    private int numberOfTable;
    private Date eventDate;

    public Order() {
    }
    
    public Order(String customerCode, String setMenuCode, int numberOfTable, Date eventDate) {
        this.orderId = generateOrderCode();
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.numberOfTable = numberOfTable;
        this.eventDate = eventDate;
    }

    public Order(String orderId, String customerCode, String codeOfSetMenu, int numberOfTable, Date eventDate) {
        this.orderId = orderId;
        this.customerCode = customerCode;
        this.setMenuCode = codeOfSetMenu;
        this.numberOfTable = numberOfTable;
        this.eventDate = eventDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerCode=" + customerCode + ", setMenuCode=" + setMenuCode + ", numberOfTable=" + numberOfTable + ", eventDate=" + eventDate + '}';
    }
    

   
      public String generateOrderCode() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(now);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.customerCode);
        hash = 97 * hash + Objects.hashCode(this.setMenuCode);
        hash = 97 * hash + Objects.hashCode(this.eventDate);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerCode, other.customerCode)) {
            return false;
        }
        if (!Objects.equals(this.setMenuCode, other.setMenuCode)) {
            return false;
        }
        return Objects.equals(this.eventDate, other.eventDate);
    }
    
    public void display(Customers customers, SetMenus setMenus){
        System.out.println("----------------------------------------------------------");
        System.out.println("Customer order information [Order Id: " + this.getOrderId() + "]");
        System.out.println("----------------------------------------------------------");
        Customer c = customers.searchById(this.getCustomerCode());
        if(c!=null){
            c.display();
        }else{
            System.out.println("Customer not found!");
        }
        System.out.println("----------------------------------------------------------");
        SetMenu sm = setMenus.searchById(this.getSetMenuCode());
        if(sm!=null){
                sm.display(this.eventDate,this.numberOfTable);
                System.out.println("----------------------------------------------------------");
                double total = sm.getPrice()*this.getNumberOfTable();
                System.out.printf("%-18s:%s\n","Total cost",String.format("%,.0f Vnd", total));
               
        }else{
            System.out.println("Set Menu not found");
        }
        System.out.println("----------------------------------------------------------");
    }
}
