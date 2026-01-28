import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class BudgetReportSystem {
    private JFrame frame;
    private JTextField budgetField;
    private JComboBox<String> categoryDropdown;
    private DefaultListModel<String> reportModel;
    private JList<String> reportList;
    private HashMap<String, Double> categoryBudgets = new HashMap<>();
    private HashMap<String, Double> categoryExpenses = new HashMap<>();
    private String[] defaultCategories = {"Food", "Transport", "Bills"};

    public BudgetReportSystem() {
        frame = new JFrame("Budget & Report System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(44, 62, 80));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(52, 73, 94));
        
        JLabel budgetLabel = new JLabel("Set Budget:");
        budgetLabel.setForeground(Color.WHITE);
        budgetField = new JTextField();
        
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(Color.WHITE);
        categoryDropdown = new JComboBox<>(defaultCategories);
        
        JButton setBudgetButton = new JButton("Set Budget");
        JButton generateReportButton = new JButton("Generate Report");
        
        styleButton(setBudgetButton, new Color(46, 204, 113));
        styleButton(generateReportButton, new Color(52, 152, 219));
        
        inputPanel.add(budgetLabel);
        inputPanel.add(budgetField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryDropdown);
        inputPanel.add(setBudgetButton);
        
        frame.add(inputPanel, BorderLayout.NORTH);
        
        reportModel = new DefaultListModel<>();
        reportList = new JList<>(reportModel);
        reportList.setBackground(new Color(236, 240, 241));
        JScrollPane scrollPane = new JScrollPane(reportList);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(44, 62, 80));
        bottomPanel.add(generateReportButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        setBudgetButton.addActionListener(e -> setBudget());
        generateReportButton.addActionListener(e -> generateReport());
        
        frame.setVisible(true);
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
    }
    
    private void setBudget() {
        String budgetText = budgetField.getText();
        String category = (String) categoryDropdown.getSelectedItem();
        
        try {
            double budget = Double.parseDouble(budgetText);
            categoryBudgets.put(category, budget);
            JOptionPane.showMessageDialog(frame, "Budget set for " + category + ": RM " + budget);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid budget amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generateReport() {
        reportModel.clear();
        for (String category : categoryBudgets.keySet()) {
            double budget = categoryBudgets.get(category);
            double expense = categoryExpenses.getOrDefault(category, 0.0);
            String reportEntry = category + " - Budget: RM " + budget + " | Spent: RM " + expense;
            
            if (expense > budget) {
                reportEntry += " (OVER BUDGET!)";
            } else if (expense >= budget * 0.8) {
                reportEntry += " (Approaching Limit)";
            }
            
            reportModel.addElement(reportEntry);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BudgetReportSystem());
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class ExpenseTracker {
    private JFrame frame;
    private JTextField amountField;
    private JComboBox<String> categoryDropdown;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private HashMap<String, Double> transactions = new HashMap<>();
    private String[] defaultCategories = {"Food", "Transport", "Bills"};

    public ExpenseTracker() {
        frame = new JFrame("Expense Tracking System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(44, 62, 80));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(52, 73, 94));
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        amountField = new JTextField();
        
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(Color.WHITE);
        categoryDropdown = new JComboBox<>(defaultCategories);
        
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton customCategoryButton = new JButton("Add Category");
        
        styleButton(addButton, new Color(46, 204, 113));
        styleButton(deleteButton, new Color(231, 76, 60));
        styleButton(customCategoryButton, new Color(241, 196, 15));
        
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryDropdown);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        
        frame.add(inputPanel, BorderLayout.NORTH);
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setBackground(new Color(236, 240, 241));
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(44, 62, 80));
        bottomPanel.add(customCategoryButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        addButton.addActionListener(e -> addTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());
        customCategoryButton.addActionListener(e -> addCustomCategory());
        
        frame.setVisible(true);
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
    }
    
    private void addTransaction() {
        String amountText = amountField.getText();
        String category = (String) categoryDropdown.getSelectedItem();
        
        try {
            double amount = Double.parseDouble(amountText);
            String entry = category + " - RM " + amount;
            transactions.put(entry, amount);
            listModel.addElement(entry);
            amountField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteTransaction() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = listModel.getElementAt(selectedIndex);
            transactions.remove(selectedValue);
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a transaction to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void addCustomCategory() {
        String newCategory = JOptionPane.showInputDialog(frame, "Enter new category:");
        if (newCategory != null && !newCategory.trim().isEmpty()) {
            categoryDropdown.addItem(newCategory);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker());
    }
}

