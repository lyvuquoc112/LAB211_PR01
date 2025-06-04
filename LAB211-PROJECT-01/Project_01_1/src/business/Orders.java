/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.SetMenu;

/**
 *
 * @author hanly
 */
public class Orders extends ArrayList<Order> implements Workable<Order, String> {
    
    public Orders() {
    }
    
    private boolean saved;
    private String pathFile;

    public Orders(String pathFile) {
        this.pathFile = pathFile;
        this.saved = false;
    }
    
    public boolean isDupplicate(Order o){
        return this.contains(o);
    }
    
    @Override
    public void addNew(Order t) {
        if(!isDupplicate(t)){
            this.add(t);
            this.saved = false;
        }
    }

    @Override
    public void upDate(Order t) {
        // Tìm đơn hàng cũ trong danh sách
        for (int i = 0; i < this.size(); i++) {
            Order currentOrder = this.get(i);
            // So sánh orderId để tìm đơn hàng cần cập nhật
            if (currentOrder.getOrderId().equals(t.getOrderId())) {
                //currentOrder.setCustomerCode(t.getCustomerCode());
                currentOrder.setSetMenuCode(t.getSetMenuCode());
                currentOrder.setNumberOfTable(t.getNumberOfTable());
                currentOrder.setEventDate(t.getEventDate());
                return;
            }
        }
        this.saved = false;
    }

    public void showAll(Customers customers, SetMenus setMenus) {
        if (this.isEmpty()) {
            System.out.println("No data in the system");
            return;
        }
    
    // Convert to ArrayList for sorting
        ArrayList<Order> sortedOrders = new ArrayList<>(this);
        Collections.sort(sortedOrders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getEventDate().compareTo(o2.getEventDate());
            }
        });

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.format("%-8s | %-12s | %-12s | %-10s | %-18s | %-7s | %-12s\n", 
            "ID", "Event date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
        System.out.println("-----------------------------------------------------------------------------------");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Order order : sortedOrders) {
            SetMenu menu = setMenus.searchById(order.getSetMenuCode());
            double totalCost = order.getNumberOfTable()* menu.getPrice();

            System.out.format("%-8s | %-12s | %-12s | %-10s | %-18s | %7d | %-12s\n",
                order.getCustomerCode(),
                sdf.format(order.getEventDate()),
                order.getCustomerCode(),
                order.getSetMenuCode(),
                String.format("%,.0f Vnd", menu.getPrice()),
                order.getNumberOfTable(),
                String.format("%,.0f Vnd", totalCost));
        }
    System.out.println("-----------------------------------------------------------------------------------");
}

    @Override
    public Order searchById(String k) {
        for (Order order : this) {
            if (order.getOrderId().equals(k)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void showAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public void saveToFile(){
        if(this.saved) {
            return;
     }
         
        FileOutputStream fos = null;
        try {
            File f = new File(pathFile);
            if(!f.exists()){
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Order thi : this) {
                oos.writeObject(thi);
            }
            oos.close();
            this.saved = true;  
        } catch (Exception e) {
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }
}
