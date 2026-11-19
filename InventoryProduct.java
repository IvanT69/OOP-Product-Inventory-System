/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PWS 5490
 */
public class InventoryProduct extends Product {

    public InventoryProduct(String id, String name, double price, int quantity) {
        super(id, name, price, quantity); // Initializes parent fields
    }

    // Additional method specific to InventoryProduct
    public double calculateStockValue() {
        return getPrice() * getQuantity(); //Use inherited methods from product
    }

    // Override displayDetails() from parent(Product)
    @Override
    public void displayDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Product ID    : " + getId());
        System.out.println("Product Name  : " + getName());
        System.out.println("Unit Price    : RM" + String.format("%.2f", getPrice()));
        System.out.println("Quantity      : " + getQuantity());
        System.out.println("Stock Value   : RM" + String.format("%.2f", calculateStockValue()));
        System.out.println("--------------------------------------------------");
    }

    // Helper method for file writing
    public String toFileString() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity();
    }
}
