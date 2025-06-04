/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import model.Customer;

/**
 *
 * @author hanly
 */
public class Customers extends HashSet<Customer> implements Workable<Customer, String>{ 
    // dùng HashSet bởi một customerCode thì chỉ có một giá trị thôi
    
    public Customers(){
        this.add(new Customer("C0001", "Nguyen Van An", "0901234567", "nguyenvanan@gmail.com"));
        this.add(new Customer("G0002", "Tran Thi Binh", "0912345678", "tranthibinh@yahoo.com"));
        this.add(new Customer("K0003", "Le Van Cuong", "0923456789", "levancuong@hotmail.com"));
        this.add(new Customer("C0004", "Pham Thi Dung", "0934567890", "phamthidung@outlook.com"));
        this.add(new Customer("G0005", "Hoang Van Em", "0945678901", "hoangvanem@gmail.com"));
        this.add(new Customer("K0006", "Vu Thi Fang", "0956789012", "vuthifang@yahoo.com"));
        this.add(new Customer("C0007", "Dao Van Giang", "0967890123", "daovangiang@gmail.com"));
        this.add(new Customer("G0008", "Bui Thi Hoa", "0978901234", "buithihoa@hotmail.com"));
        this.add(new Customer("K0009", "Ly Van Inh", "0989012345", "lyvaninh@outlook.com"));
        this.add(new Customer("C0010", "Do Thi Kim", "0990123456", "dothikim@gmail.com"));
    }
    
    public boolean isDuppilcate(Customer c){
        return this.contains(c);
    }
    @Override
    public void addNew(Customer t) {
        if(!isDuppilcate(t)){
            this.add(t);
        }else
            System.out.println("Error: Customer Aldready Exists!");
    }

    @Override
    public void upDate(Customer t) {
        this.remove(t);
        this.add(t);
    }

    // Phương thức private để hiển thị danh sách khách hàng
    private void displayCustomerList(ArrayList<Customer> customerList) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-10s | %-20s\n", "Code","Customer Name","Phone","Email");
        System.out.println("-------------------------------------------------------------------------");
        
        Collections.sort(customerList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        
        for (Customer customer : customerList) {
            System.out.printf("%-5s | %-25s | %-10s | %-20s\n",
                    customer.getCustomerCode(),
                    customer.getName(),
                    customer.getPhoneNumber(),
                    customer.getEmail());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    @Override
    public void showAll() {
        ArrayList<Customer> sortedList = new ArrayList<>(this);
        displayCustomerList(sortedList);
    }

    public void showFilteredByName(HashSet<Customer> h) {
        ArrayList<Customer> sortedList = new ArrayList<>(h);
        displayCustomerList(sortedList);
    }

    @Override
    public Customer searchById(String k) {
        Customer customer = null; 
        for (Customer thi : this) {
            if(thi.getCustomerCode().equalsIgnoreCase(k)){
                return thi;
            }
        }return customer;
    }
    
    public HashSet<Customer> filteredByName(String name){
        HashSet<Customer> result = new HashSet<>();
        for (Customer thi : this) {
            String aName = thi.getName().toUpperCase();
            String bName = name.toUpperCase();
            if(aName.contains(bName)){
                result.add(thi);
            }
        }
        return result;
    }
    
}
