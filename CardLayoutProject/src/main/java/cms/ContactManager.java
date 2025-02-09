package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContactManager {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DefaultListModel<String> contactListModel;
    private ArrayList<Contact> contacts;
    private JList<String> contactList;
    private JTextField nameField, phoneField, emailField;
    private JLabel detailName, detailPhone, detailEmail;

    public ContactManager() {
        frame = new JFrame("Contact Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        contacts = new ArrayList<>();
        contactListModel = new DefaultListModel<>();

        mainPanel.add(createContactListView(), "List");
        mainPanel.add(createContactDetailsView(), "Details");
        mainPanel.add(createContactFormView(), "Form");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createContactListView() {
        JPanel panel = new JPanel(new BorderLayout());

        contactList = new JList<>(contactListModel);
        panel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JButton addButton = new JButton("Add New Contact");
        JButton viewButton = new JButton("View Details");

        addButton.addActionListener(e -> handleAddButton());
        viewButton.addActionListener(e -> showContactDetails());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleAddButton() {
        cardLayout.show(mainPanel, "Form");
    }

    private JPanel createContactDetailsView() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        detailName = new JLabel();
        detailPhone = new JLabel();
        detailEmail = new JLabel();
        JButton backButton = new JButton("Back to List");

        backButton.addActionListener(e -> handleBackButton());

        panel.add(detailName);
        panel.add(detailPhone);
        panel.add(detailEmail);
        panel.add(backButton);

        return panel;
    }

    private void handleBackButton() {
        cardLayout.show(mainPanel, "List");
    }

    private JPanel createContactFormView() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        JButton saveButton = new JButton("Save Contact");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> handleSaveButton());
        cancelButton.addActionListener(e -> handleCancelButton());

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(saveButton);
        panel.add(cancelButton);

        return panel;
    }

    private void handleSaveButton() {
        saveContact();
    }

    private void handleCancelButton() {
        cardLayout.show(mainPanel, "List");
    }

    private void saveContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        contacts.add(new Contact(name, phone, email));
        contactListModel.addElement(name);
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        cardLayout.show(mainPanel, "List");
    }

    private void showContactDetails() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a contact!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contact contact = contacts.get(selectedIndex);
        detailName.setText("Name: " + contact.getName());
        detailPhone.setText("Phone: " + contact.getPhone());
        detailEmail.setText("Email: " + contact.getEmail());

        cardLayout.show(mainPanel, "Details");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManager::new);
    }
}

