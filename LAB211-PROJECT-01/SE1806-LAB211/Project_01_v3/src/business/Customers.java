
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import models.Customer;

public class Customers extends HashSet<Customer> implements Workable<Customer, String> {

    public Customers() {
         // Tạo sẵn một số customer mẫu
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

    public boolean isDupplicate(Customer t) {
        return this.contains(t);
        //        for (Customer c : this) {
        //            if (c.getCustomerCode().equals(t.getCustomerCode())) {
        //                return true;
        //            }
        //        }
        //        return false;
    }

    @Override
    public void addNew(Customer t) {
        if (!this.isDupplicate(t)) {
            this.add(t);
        } else {
            System.out.println("Error: Customer Aldready Exists!");
        }
    }

    @Override
    public void update(Customer t) {
        this.remove(t);
        this.add(t);
    }

    @Override
    public void showAll() {
    if (this.isEmpty()) {
        System.out.println("Does not have any customer information.");
        return;
    }
    
    // Convert to ArrayList for sorting
    ArrayList<Customer> sortedCustomers = new ArrayList<>(this);
    Collections.sort(sortedCustomers, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    
    System.out.println("Customers information:");
    System.out.println("----------------------------------------------------------------");
    System.out.format("%-8s | %-20s | %-11s | %-20s%n", "Code", "Customer Name", "Phone", "Email");
    System.out.println("----------------------------------------------------------------");
    for (Customer c : sortedCustomers) {
        System.out.format("%-8s | %-20s | %-11s | %-20s%n", 
            c.getCustomerCode(), c.getName(), c.getPhone(), c.getEmail());
    }
    System.out.println("----------------------------------------------------------------");
}

    @Override
    public Customer searchById(String id) {
        for (Customer c : this) {
            if (c.getCustomerCode().equals(id)) {
                return c;
            }
        }
        return null;
    }
    
    
    public void showFilteredResult(HashSet<Customer> filterCustome, String name){
        if(filterCustome.isEmpty()){
            System.out.println("No one matches the search criterial");
            return;
        }
        ArrayList<Customer> sortCusomters = new ArrayList<>(filterCustome);
        Collections.sort(sortCusomters, (o1,o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        System.out.println("------------------------------------------------------------------");
        System.out.format("%-8s | %-20s | %-11s | %-20s%n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("------------------------------------------------------------------");
        for (Customer c : sortCusomters) {
            System.out.format("%-8s | %-20s | %-11s | %-20s%n", 
                c.getCustomerCode(), c.getName(), c.getPhone(), c.getEmail());
        }
        System.out.println("------------------------------------------------------------------");
}

    public HashSet<Customer> filterByName(String name) {
        HashSet<Customer> result = new HashSet<>();
        for (Customer c : this) {
            String cName = c.getName().toUpperCase();
            String kName = name.toUpperCase();
            if (cName.indexOf(kName) > -1) {
                result.add(c);
            }
        }
        return result;
    }
}
