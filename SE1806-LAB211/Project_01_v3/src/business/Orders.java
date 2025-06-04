/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import models.Order;
import models.SetMenu;

/**
 *
 * @author tungi
 */
public class Orders extends ArrayList<Order> implements Workable<Order, String>{

    @Override
    public void addNew(Order t) {
        if(!isDupplicated(t)){
            this.add(t);
        }
    }

    @Override
    public void update(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
   public void showAll() {
    // This method is not used directly, we'll use showAll(Customers, SetMenus) instead
}

    public void showAll(Customers customers, SetMenus setMenus) {
        if (this.isEmpty()) {
            System.out.println("Does not have any order information.");
            return;
        }
    
    // Convert to ArrayList for sorting
    ArrayList<Order> sortedOrders = new ArrayList<>(this);
    Collections.sort(sortedOrders, (o1, o2) -> o1.getEventDate().compareTo(o2.getEventDate()));
    
    System.out.println("-------------------------------------------------------------------------");
    System.out.format("%-8s | %-12s | %-12s | %-10s | %-12s | %-7s | %-12s%n", 
        "ID", "Event date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
    System.out.println("-------------------------------------------------------------------------");
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    for (Order order : sortedOrders) {
        SetMenu menu = setMenus.searchById(order.getCodeOfSetMenu());
        double totalCost = order.getNumberOfTables() * menu.getPrice();
        
        System.out.format("%-8s | %-12s | %-12s | %-10s | %,-12.0f | %-7d | %,-12.0f%n",
            order.getOrderId(),
            sdf.format(order.getEventDate()),
            order.getCustomerCode(),
            order.getCodeOfSetMenu(),
            menu.getPrice(),
            order.getNumberOfTables(),
            totalCost);
    }
    System.out.println("-------------------------------------------------------------------------");
}
    @Override
    public Order searchById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean isDupplicated(Order o){
        return this.contains(o);
    }

    public boolean canUpdate(Order order) {
        // Kiểm tra xem ngày tổ chức có trong quá khứ không
        Date today = new Date();
        return order.getEventDate().after(today);
    }
}
