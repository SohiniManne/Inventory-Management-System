import java.util.*;
import java.io.*;

public class InventoryManager {
    private List<Product> products = new ArrayList<>();
    private final String filename = "inventory.txt";

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product searchProduct(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public void removeProduct(String name) {
        products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Product p : products) pw.println(p.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        products.clear();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                products.add(Product.fromString(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No inventory file found. Starting fresh.");
        }
    }
}
