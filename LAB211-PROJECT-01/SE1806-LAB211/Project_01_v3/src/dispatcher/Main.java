
package dispatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.util.HashSet;
import java.util.Scanner;
import models.Customer;
import tools.Inputter;
import models.Order;



public class Main {

    public static void main(String[] args) {
        Inputter inputter = new Inputter();
        Customers customers = new Customers();
        Orders orders = new Orders();
        SetMenus setmenus = new SetMenus("D:\\FPT\\Ky 3\\LAB211\\SE1806-LAB211-main\\Set14_SU25\\De_LAB211\\01_J1.L.P0028.TraditionalFeastOrderManagement_300LOC\\FeastMenu.csv");

        Scanner scanner = new Scanner(System.in);
        int option = 0; 
        int testCase = 10;
        do {
            System.out.println("\n----------MAIN MENU------------");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Seach for customer information by name");
            System.out.println("4. Display feast menu");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update a feast order");
            System.out.println("8. Display customers or order list");
            System.out.println("10. Exit");
            System.out.print("Enter Test Case No. : ");
            testCase = Integer.parseInt(scanner.nextLine());
            switch (testCase) {
                case 1:
                    
                    do {
                        customers.addNew(inputter.inputCustomer(false));
                        System.out.println("1. Continue entering new customers");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Enter your option: ","Option must be 1 or 2" , "^[12]$"));
                    } while (option ==1 );
                    break;
                case 2: 
                    do {
                        System.out.print("Enter customer code: ");
                        String customerCode = scanner.nextLine();
                        Customer c = customers.searchById(customerCode);
                        if (c == null) {
                            System.out.println("This customer does not exist.");
                        } else {
                            Customer customer = inputter.inputCustomer(true);
                            customer.setCustomerCode(customerCode);
                            customers.update(customer);
                        }
                        System.out.println("1. Continue updating customer");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Enter your option: ","Option must be 1 or 2" , "^[12]$"));
                    } while (option ==1 );
                    break;
                case 3:
                  
                    do {
                        System.out.print("Enter customer name: ");
                        String name = scanner.nextLine();
                        if(!name.isEmpty()){
                            HashSet<Customer> filteredCustomers = customers.filterByName(name);
                            customers.showFilteredResult(filteredCustomers, name);
                        }else{
                            System.out.println("Please enter a name to search");
                        }
                        
                        
                        System.out.println("1. Continue search");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Enter your option: ","Option must be 1 or 2" , "^[12]$"));
                    } while (option ==1 );
                    break;
                case 4: {
                    try {
                        setmenus.readFromFile();
                    } catch (Exception e) {
                    }

                    setmenus.showAll();
                }
                break;
                case 5:
                    do {                        
                        Order order = inputter.inputOrder(customers, setmenus, false);
                        if (orders.contains(order)) {
                            System.out.println("Dupplicate data!");
                        } else {
                            orders.addNew(order);
                            order.display(customers, setmenus);
                        }
                        System.out.println("1. Continue order");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Enter your option: ","Option must be 1 or 2" , "^[12]$"));
                  
                    } while (option == 1);
                    

                    break;
                case 6:
                    option = 0;
                    do {
                        System.out.print("Enter Order ID: ");
                        String orderId = scanner.nextLine().trim();
                        Order order = orders.searchById(orderId);
                        
                        if (order == null) {
                            System.out.println("This Order does not exist.");
                        } else if (!orders.canUpdate(order)) {
                            System.out.println("Cannot update order with past event date.");
                        } else {
                            // Hiển thị thông tin hiện tại
                            System.out.println("\nCurrent order information:");
                            order.display(customers, setmenus);
                            
                            // Cập nhật thông tin mới
                            Order updatedOrder = inputter.inputOrder(customers, setmenus, true);
                            if (updatedOrder != null) {
                                // Giữ nguyên orderId và customerCode
                                updatedOrder.setOrderId(orderId);
                                updatedOrder.setCustomerCode(order.getCustomerCode());
                                orders.update(updatedOrder);
                                System.out.println("\nOrder updated successfully!");
                                updatedOrder.display(customers, setmenus);
                            }
                        }
                        
                        System.out.println("1. Continue updating orders");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Enter your option: ", 
                            "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 8:
                    System.out.println("--------------DISPLAY MENU---------------");
                    System.out.println("1. Display Customer List");
                    System.out.println("2. Display Order List");
                    System.out.println("3. Return to the menu");
                    option = Integer.parseInt(inputter.input("Enter your option: ","Option must be 1,2 or 3" , "^[123]$"));  
                    switch (option) {
                        case 1:
                            if(customers.isEmpty()){
                                System.out.println("Does not have any customer information. ");
                            }else{
                                customers.showAll();
                            }
                            break;
                        case 2:
                            if(orders.isEmpty()){
                                System.out.println("Does not have any order infomation");
                            }else{
                                orders.showAll(customers, setmenus);
                            }
                            break;
                        default:
                           System.out.println("Invalid option");
                    }
                    break;
                case 10:
                    System.out.println("Thank you for using our company services");
                    break;
                default:
                    throw new AssertionError();
            }
        } while (testCase != 10);
    }
}
