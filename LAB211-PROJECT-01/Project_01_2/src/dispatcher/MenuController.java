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
import model.Customer;
import model.Order;
import tool.Inputter;

/**
 *
 * @author hanly
 */
public class MenuController {

    private final Inputter inputter;
    private final Customers customers;
    private final Orders orders;
    private final SetMenus setMenus;
    private final Scanner sc;

    public MenuController() {
        inputter = new Inputter();
        customers = new Customers("customers.dat");
        orders = new Orders("feast_order_service.dat");
        setMenus = new SetMenus("D:\\FPT\\Ky 3\\LAB211\\SE1806-LAB211-main\\Set14_SU25\\De_LAB211\\01_J1.L.P0028.TraditionalFeastOrderManagement_300LOC\\FeastMenu.csv");
        sc = new Scanner(System.in);
    }

    public String getScanner() {
        return sc.nextLine();
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                handleRegisterCustomers();
                break;
            case 2:
                handleUpdateCustomer();
                break;
            case 3:
                handleSearchCustomer();
                break;
            case 4:
                handleDisplayMenu();
                break;
            case 5:
                handlePlaceOrder();
                break;
            case 6:
                handleUpdateOrder();
                break;
            case 7:
                handleSaveData();
                break;
            case 8:
                handleDisplayData();
                break;
            case 9:
                System.out.println("Thank you for using our company service");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void handleRegisterCustomers() {
        int option = 0;
        do {
            customers.addNew(inputter.inputCustomer(null, false));
            System.out.println("1. Continue entering new customers");
            System.out.println("2. Return to the main menu");
            option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
        } while (option == 1);
    }

    private void handleUpdateCustomer() {
        int option = 0;
        do {
            System.out.println("Enter customer code");
            String customerCode = sc.nextLine().toUpperCase();
            Customer customer = customers.searchById(customerCode);
            if (customer == null) {
                System.out.println("This customer does not exist");
            } else {
                Customer c = inputter.inputCustomer(customer, true);
                c.setCustomerCode(customerCode);
                customers.upDate(c);
            }
            System.out.println("1. Continue update customers");
            System.out.println("2. Return to the main menu");
            option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
        } while (option == 1);
    }

    private void handleSearchCustomer() {
        int option = 0;
        do {
            System.out.println("Enter name: ");
            String name = sc.nextLine();
            HashSet<Customer> result = customers.filteredByName(name);
            if (result.isEmpty()) {
                System.out.println("No one matches the search criteria!");
            } else {
                customers.showFilteredByName(result);
            }
            System.out.println("1. Continue search customers by name");
            System.out.println("2. Return to the main menu");
            option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
        } while (option == 1);
    }

    private void handleDisplayMenu() {
        try {
            setMenus.readFormFile();
        } catch (Exception e) {
        }
        setMenus.showAll();
    }

    private void handlePlaceOrder() {
        int option = 0;
        do {
            Order order = inputter.inputOrder(customers, setMenus, false, null);
            if (order != null) {
                orders.addNew(order);
                order.display(customers, setMenus);
            }
            System.out.println("1. Continue register order");
            System.out.println("2. Return to the main menu");
            option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
        } while (option == 1);
    }

    private void handleUpdateOrder() {
        int option = 0;
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
                    
                }
            }
            System.out.println("1. Continue updating orders");
            System.out.println("2. Return to the main menu");
            option = Integer.parseInt(inputter.input("Choose your option", "Option must be 1 or 2", "^[12]$"));
        } while (option == 1);
    }

    private void handleSaveData() {
        int option = 0;
        System.out.println("Display options:");
        System.out.println("1. Save customers to file");
        System.out.println("2. Save order to file");
        System.out.print("Enter your choice: ");
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                customers.saveToFile();
                System.out.println("The data is successfully saved!");
                break;
            case 2:
                orders.saveToFile();
                System.out.println("The data is successfully saved!");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void handleDisplayData() {
        System.out.println("Display options:");
        System.out.println("1. Display customers list");
        System.out.println("2. Display orders list");
        System.out.print("Enter your choice: ");

        int displayChoice = Integer.parseInt(sc.nextLine());
        switch (displayChoice) {
            case 1:
                Customers customersTemp = new Customers("customers.dat");
                if (customersTemp.size() <= 0) {
                    System.out.println("No data in the system");
                } else {
                    customersTemp.showAll();
                }
                break;
            case 2:
                Orders ordersTemp = new Orders("feast_order_service.dat");
                if (ordersTemp.size() <= 0) {
                    System.out.println("No data in the system");
                } else {
                    ordersTemp.showAll(customers, setMenus);
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

}
