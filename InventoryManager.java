/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PWS 5490
 */
import java.io.*;

public class InventoryManager {
    private InventoryProduct[] products; // 1D Array of Objects
    private int count;
    private final int MAX_CAPACITY = 100;
    private final String fileName = "products.txt";

    public InventoryManager() {
        products = new InventoryProduct[MAX_CAPACITY];
        count = 0;
    }

    // ADD Operation (CRUD)
    public boolean addProduct(InventoryProduct product) {
        if (count >= MAX_CAPACITY) {
            System.out.println("Inventory is full!");
            return false;
        }
        if (searchProduct(product.getId()) != null) {
            System.out.println("Error: Product ID already exists!");
            return false;
        }
        products[count++] = product;
        System.out.println("Product added successfully.");
        return true;
    }

    // SEARCH Operation (CRUD)
    public InventoryProduct searchProduct(String id) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId().equalsIgnoreCase(id)) {
                return products[i];
            }
        }
        return null;
    }

    // UPDATE Operation (CRUD)
    public boolean updateProduct(String id, String newName, double newPrice, int newQty) {
        InventoryProduct product = searchProduct(id);
        if (product != null) {
            product.setName(newName);
            product.setPrice(newPrice);
            product.setQuantity(newQty);
            System.out.println("Product updated successfully.");
            return true;
        }
        System.out.println("Product not found.");
        return false;
    }

    // DELETE Operation (CRUD)
    public boolean deleteProduct(String id) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId().equalsIgnoreCase(id)) {
                // Shift array elements left
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }
                products[count - 1] = null;
                count--;
                System.out.println("Product deleted successfully.");
                return true;
            }
        }
        System.out.println("Product not found for deletion.");
        return false;
    }

    // DISPLAY Products
    public void displayProducts() {
        if (count == 0) {
            System.out.println("No products available in the inventory.");
            return;
        }
        System.out.println("\n--- INVENTORY PRODUCT LIST ---");
        for (int i = 0; i < count; i++) {
            products[i].displayDetails();
        }
    }

    // File Handling: Save
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < count; i++) {
                writer.println(products[i].toFileString());
            }
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // File Handling: Load
    public void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return; // No file yet, silently skip loading
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            count = 0; // Reset count
            while ((line = reader.readLine()) != null && count < MAX_CAPACITY) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    products[count++] = new InventoryProduct(id, name, price, quantity);
                }
            }
            System.out.println("Records loaded correctly from " + fileName);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
