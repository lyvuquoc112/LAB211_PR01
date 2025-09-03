        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author hanly
 */
public class Customers extends HashSet<Customer> implements Workable<Customer, String>{ 
    // dùng HashSet bởi một customerCode thì chỉ có một giá trị thôi
    private boolean saved;
    private String pathFile;

    public Customers(String pathFile) {
        this.pathFile = pathFile;
        this.readFromFile();
    }
    
    
    
    public boolean isDuppilcate(Customer c){
        return this.contains(c);
    }
    @Override
    public void addNew(Customer t) {
        if(!isDuppilcate(t)){
            this.add(t);
            this.saved = false;
        }else{
            System.out.println("Error: Customer Aldready Exists!");
        }
    }

    @Override
    public void update(Customer t) {
        this.remove(t);
        this.add(t);
        this.saved = false;
    }

    @Override
    public void showAll() {
        showCustomerList(this);
    }

    public void showFilteredByName(HashSet<Customer> h) {
        showCustomerList(h);
    }

    // Phương thức mới để hiển thị danh sách khách hàng
    private void showCustomerList(Collection<Customer> customers) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-10s | %-20s\n", "Code","Customer Name","Phone","Email");
        System.out.println("-------------------------------------------------------------------------");
        
        ArrayList<Customer> sortedList = new ArrayList<>(customers);
        Collections.sort(sortedList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        
        for (Customer customer : sortedList) {
            System.out.printf("%-5s | %-25s | %-10s | %-20s\n",
                    customer.getCustomerCode(),
                    customer.getName(),
                    customer.getPhoneNumber(),
                    customer.getEmail());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    @Override
    public Customer searchById(String k) {
        for (Customer thi : this) {
            if(thi.getCustomerCode().equalsIgnoreCase(k)){
                return thi;
            }
        }return null;
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
            for (Customer thi : this) {
                oos.writeObject(thi);
            }
            oos.close();
            this.saved = true;  
        } catch (Exception e) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void readFromFile(){
        FileInputStream fis = null;
        try {
            File f = new File(this.pathFile);
            if(!f.exists()){
                System.out.println("Cannot read data form "+this.pathFile+" Please check it");
                return;
            }else{
                fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    while (true) {                        
                        Object o = ois.readObject();
                        Customer c = (Customer) o;
                        this.addNew(c);
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }finally{
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
    }

}
