import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryApp extends JFrame {
    private InventoryManager manager = new InventoryManager();
    private JTextArea displayArea;

    public InventoryApp() {
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JTextField nameField = new JTextField(10);
        JTextField qtyField = new JTextField(5);
        JTextField priceField = new JTextField(5);

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton deleteBtn = new JButton("Delete");
        JButton saveBtn = new JButton("Save");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Qty:"));
        panel.add(qtyField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(deleteBtn);
        panel.add(saveBtn);

        add(panel, BorderLayout.NORTH);

        // Load existing data
        manager.loadFromFile();

        // Button actions
        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());
                manager.addProduct(new Product(name, qty, price));
                displayArea.setText("Product added: " + name);
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid input.");
            }
        });

        viewBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Product p : manager.getAllProducts()) {
                sb.append(p.getName()).append(" | Qty: ").append(p.getQuantity()).append(" | ₹").append(p.getPrice()).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        deleteBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter product name to delete:");
            manager.removeProduct(name);
            displayArea.setText("Product removed: " + name);
        });

        saveBtn.addActionListener(e -> {
            manager.saveToFile();
            displayArea.setText("Inventory saved to file.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryApp().setVisible(true);
        });
    }
}
