/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;


/**
 *
 * @author hanly
 */
public class Main {

    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        int choice = 0;
        do {
            System.out.println("\n----------MAIN MENU------------");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Search for customer information by name");
            System.out.println("4. Display feast menu");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update a feast order");
            System.out.println("7. Save data to file");
            System.out.println("8. Display customers or order list");
            System.out.println("9. Exit");
            System.out.print("Enter Test Case No. : ");
            choice = Integer.parseInt(menuController.getScanner());
            menuController.processChoice(choice);

        } while (choice != 9);
    }
}
