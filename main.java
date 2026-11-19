/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author PWS 5490
 */
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
        
        // Load data on startup
        manager.loadFromFile();

        int choice = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("   RETAIL MANAGEMENT SYSTEM - INVENTORY ");
            System.out.println("========================================");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Display All Products");
            System.out.println("6. Save to File");
            System.out.println("7. Load from File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        String id = scanner.nextLine().trim();
                        if (id.isEmpty()) {
                            System.out.println("Error: ID cannot be empty.");
                            break;
                        }
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Error: Name cannot be empty.");
                            break;
                        }
                        
                        double price = -1;
                        while (price < 0) {
                            try {
                                System.out.print("Enter Unit Price (RM): ");
                                price = Double.parseDouble(scanner.nextLine());
                                if (price < 0) System.out.println("Price cannot be negative.");
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format. Enter a valid price.");
                            }
                        }

                        int quantity = -1;
                        while (quantity < 0) {
                            try {
                                System.out.print("Enter Quantity: ");
                                quantity = Integer.parseInt(scanner.nextLine());
                                if (quantity < 0) System.out.println("Quantity cannot be negative.");
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid integer format. Enter a valid quantity.");
                            }
                        }

                        InventoryProduct newProd = new InventoryProduct(id, name, price, quantity);
                        manager.addProduct(newProd);
                        break;

                    case 2:
                        System.out.print("Enter Product ID to search: ");
                        String searchId = scanner.nextLine().trim();
                        InventoryProduct found = manager.searchProduct(searchId);
                        if (found != null) {
                            System.out.println("Product found with details:");
                            found.displayDetails();
                        } else {
                            System.out.println("Product with ID '" + searchId + "' not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to update: ");
                        String updateId = scanner.nextLine().trim();
                        InventoryProduct toUpdate = manager.searchProduct(updateId);
                        if (toUpdate != null) {
                            System.out.print("Enter New Product Name: ");
                            String newName = scanner.nextLine().trim();
                            
                            double newPrice = -1;
                            while (newPrice < 0) {
                                try {
                                    System.out.print("Enter New Unit Price (RM): ");
                                    newPrice = Double.parseDouble(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number format.");
                                }
                            }

                            int newQty = -1;
                            while (newQty < 0) {
                                try {
                                    System.out.print("Enter New Quantity: ");
                                    newQty = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number format.");
                                }
                            }
                            manager.updateProduct(updateId, newName, newPrice, newQty);
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Product ID to delete: ");
                        String deleteId = scanner.nextLine().trim();
                        manager.deleteProduct(deleteId);
                        break;

                    case 5:
                        manager.displayProducts();
                        break;

                    case 6:
                        manager.saveToFile();
                        break;

                    case 7:
                        manager.loadFromFile();
                        break;

                    case 8:
                        manager.saveToFile(); // Autosave on exit
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid menu option number.");
            }
            
        } while (choice != 8);
        scanner.close();
    }
}
