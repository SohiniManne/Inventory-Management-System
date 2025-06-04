public class Product {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return name + "," + quantity + "," + price;
    }

    public static Product fromString(String data) {
        String[] parts = data.split(",");
        return new Product(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
    }
}
