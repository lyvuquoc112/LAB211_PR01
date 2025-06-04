/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import business.Customers;
import business.SetMenus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Customer;
import model.Order;

/**
 *
 * @author hanly
 */
public class Inputter {
    private Scanner sc;
    
    public Inputter(){
        sc = new Scanner(System.in);
    }
    
    public String getString(String mess){
        System.out.println(mess);
        return sc.nextLine();
    }
    
    public int getInt(String mess){
        String input = getString(mess);
        int result = 0;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e) {
        }
        return result;
    }
    
    public double getDouble(String mess){
        String input = getString(mess);
        double result = 0;
        try {
            result = Double.parseDouble(input);
        } catch (Exception e) {
        }
        return result;
    }
    
    public String input(String mess, String errorMess, String regex){
        String input;
        boolean more = false;
        do {            
            input = getString(mess);
            more = Validator.isValid(input, regex);
            if(!more){
                System.out.println(errorMess+ " Please Re-enter.");
            }
        } while (!more);
        return input;
    }
    
    public Customer inputCustomer(Customer oldCustomer, boolean isUpdate) {
        Customer customer = new Customer();

        // Customer code
        if (!isUpdate) {
            String mess = "Input Customer Code (the first character is [C,G,K], followed by 4 digits): ";
            String errorMess = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
            String regex = Validator.CUSTOMER_CODE_REGEX;
            customer.setCustomerCode(input(mess, errorMess, regex).toUpperCase());
        }
        
        boolean isSetName = false;
        do {            
            String mess = isUpdate ? "Input name [" + oldCustomer.getName() + "]: " : "Input name: ";
            String name = getString(mess);
            if (isUpdate && name.isEmpty()) {
                customer.setName(oldCustomer.getName());
                isSetName = true;
            } else if (Validator.isValid(name, Validator.NAME_REGEX)) {
                customer.setName(name);
                isSetName = true;
            } else {
                System.out.println("Name must be between 2 and 25 characters. Please re-enter.");
            }
        } while (!isSetName);
        
        boolean isPhone = false;
        do {            
            String mess = isUpdate ? "Input phone [" + oldCustomer.getPhoneNumber() + "]: " : "Input phone: ";
            String phone = getString(mess);
            if (isUpdate && phone.isEmpty()) {
                customer.setPhoneNumber(oldCustomer.getPhoneNumber());
                isPhone = true;
            } else if (Validator.isValid(phone, Validator.PHONE_NUMBER_REGEX)) {
                customer.setPhoneNumber(phone);
                isPhone = true;
            } else {
                System.out.println("Invalid phone format! Please re-enter.");
            }
        } while (!isPhone);
        
        boolean isEmail = false;
        do {            
            String mess = isUpdate ? "Input email [" + oldCustomer.getEmail() + "]: " : "Input email: ";
            String email = getString(mess);
            if (isUpdate && email.isEmpty()) {
                customer.setEmail(oldCustomer.getEmail());
                isEmail = true;
            } else if (Validator.isValid(email, Validator.EMAIL_REGEX)) {
                customer.setEmail(email);
                isEmail = true;
            } else {
                System.out.println("Invalid email format! Please re-enter.");
            }
        } while (!isEmail);

        return customer;
    }
    
    public Order inputOrder(Customers customers, SetMenus setMenus, boolean isUpdate, Order oldOrder) {
        String customerCode = "";
        String setMenuCode = "";
        int numberOfTable = 0;
        Date eventDate = null;

        // Customer code
        if (!isUpdate) {
                String msg = "Enter Customer code:";
                String errorMsg = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
                String regex = Validator.CUSTOMER_CODE_REGEX;
                customerCode = input(msg, errorMsg, regex).toUpperCase();
        }

        
        boolean checkSetMenuCode = false;
        // Set Menu Code
        do {            
            String msg = isUpdate ? "Enter SetMenu code [" + oldOrder.getSetMenuCode() + "]: " : "Enter SetMenu code:";
            setMenuCode = getString(msg).toUpperCase();
            if (isUpdate && setMenuCode.isEmpty()) {
                setMenuCode = oldOrder.getSetMenuCode();
                checkSetMenuCode = true;
            }
            if (setMenus.searchById(setMenuCode) != null) {
                checkSetMenuCode = true;
            }else
                System.out.println("SetMenu is not exists!");
        } while (!checkSetMenuCode);
       
        
        boolean checkNumberOfTables = false;
        do {            
            String msg = isUpdate ? "Enter number of tables [" + oldOrder.getNumberOfTable() + "]: " : "Enter number of tables:";
            String tables = getString(msg);
            if (isUpdate && tables.isEmpty()) {
                numberOfTable = oldOrder.getNumberOfTable();
                checkNumberOfTables = true;
            } else {
                try {
                    numberOfTable = Integer.parseInt(tables);
                    if (numberOfTable <= 0) {
                        System.out.println("Number of tables must be greater than zero!");
                    }else
                        checkNumberOfTables = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format!");
                }
            }
        } while (!checkNumberOfTables);
        // Number of Tables
        

        boolean checkEventDate = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {            
            String msg = isUpdate ? "Enter preferred event date [" + sdf.format(oldOrder.getEventDate()) + "] (dd/MM/yyyy)"
                                    : "Enter preferred event date (dd/MM/yyyy)";
            String date = getString(msg);
            if (isUpdate && date.isEmpty()) {
                eventDate = oldOrder.getEventDate();
                checkEventDate = true;
            }else {
                    try {
                        sdf.setLenient(false);
                        eventDate = sdf.parse(date);
                        // Kiểm tra xem ngày có phải trong tương lai không
                        Date today = new Date();
                        if (eventDate.after(today)) {
                            checkEventDate = true;
                        } else {
                            System.out.println("Error: Event date must be in the future.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid date format or invalid date. Please enter a valid date (dd/MM/yyyy).");
                    }
                }
            
        } while (!checkEventDate);
                // Event Date
        
        Order order;
        if (isUpdate) {
            order = new Order(oldOrder.getOrderId(), customerCode, setMenuCode, numberOfTable, eventDate);
        } else {
            order = new Order(customerCode, setMenuCode, numberOfTable, eventDate);
        }
        return order;
    }
}
    

