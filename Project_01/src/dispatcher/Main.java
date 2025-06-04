/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.util.HashSet;
import java.util.Scanner;
import javax.xml.transform.OutputKeys;
import model.Customer;
import model.Order;
import tool.Inputter;

/**
 *
 * @author hanly
 */
public class Main {
    public static void main(String[] args) {
        Inputter inputter = new Inputter();
        Customers customers = new Customers();
        Orders orders = new Orders();
        SetMenus setMenus = new SetMenus("D:\\FPT\\Ky 3\\LAB211\\SE1806-LAB211-main\\Set14_SU25\\De_LAB211\\01_J1.L.P0028.TraditionalFeastOrderManagement_300LOC\\FeastMenu.csv");
        Scanner sc = new Scanner(System.in);
        int choice = 0, option = 0;
        do {
            System.out.println("\n----------MAIN MENU------------");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Search for customer information by name");
            System.out.println("4. Display feast menu");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update a feast order");
            System.out.println("7. --------------------");
            System.out.println("8. Display customers or order list");
            System.out.println("9. Exit");
            
            
            boolean more = false;
            do {                
                try {
                System.out.print("Enter Test Case No. : ");
                choice = Integer.parseInt(sc.nextLine());
                more = true;
                } catch (Exception e) {
                    System.out.println("Just enter 1-9. Please Re-enter");
                }
            } while (!more);
            
            
            
            switch (choice) {
                case 1:
                    do {                        
                        customers.addNew(inputter.inputCustomer(null,false));
                        System.out.println("1. Continue entering new customers");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 2:
                    do {                        
                        System.out.println("Enter customer code");
                        String customerCode = sc.nextLine().toUpperCase();
                        Customer customer = customers.searchById(customerCode);
                        if(customer == null){
                            System.out.println("This customer does not exist");
                        }else{
                            Customer c = inputter.inputCustomer(customer, true);
                            c.setCustomerCode(customerCode);
                            customers.upDate(c);
                        }
                        System.out.println("1. Continue update customers");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 3:
                    do{
                        System.out.println("Enter name: ");
                        String name = sc.nextLine();
                        HashSet<Customer> result = customers.filteredByName(name);
                        if(result.isEmpty()){
                            System.out.println("No one matches the search criteria!");
                        }else{
                            customers.showFilteredByName(result);
                        }
                        System.out.println("1. Continue search customers by name");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 4:
                    try {
                        setMenus.readFormFile();
                    } catch (Exception e) {
                    }
                    setMenus.showAll();
                    break;
                case 5:
                    do {                        
                        Order order = inputter.inputOrder(customers, setMenus, false, null);
                        if(orders.contains(order)){
                            System.out.println("Dupplicate data!");
                        }else{
                            orders.addNew(order);
                            order.display(customers, setMenus);
                        }
                        System.out.println("1. Continue register order");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 6:
                    do {
                        System.out.println("Enter order ID: ");
                        String orderId = sc.nextLine();
                        Order order = orders.searchById(orderId);
                        if (order == null) {
                            System.out.println("This order does not exist.");
                        } else {
                            Order updatedOrder = inputter.inputOrder(customers, setMenus, true, order);
                            if (updatedOrder != null) {
                                orders.upDate(updatedOrder);
                                System.out.println("Order updated successfully!");
                                updatedOrder.display(customers, setMenus);
                            }
                        }
                        System.out.println("1. Continue updating orders");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Choose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 8:
                                            
                        System.out.println("\nDisplay options:");
                        System.out.println("1. Display customers list");
                        System.out.println("2. Display orders list");
                        System.out.print("Enter your choice: ");

                        int displayChoice = Integer.parseInt(sc.nextLine());
                        switch (displayChoice) {
                            case 1:
                                customers.showAll();
                                break;
                            case 2:
                                orders.showAll(customers,setMenus);
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    
                    
                    break;
                case 9:
                    System.out.println("Thank you for using our company service");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(choice!=9);
    }
}
