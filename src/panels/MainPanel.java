package panels;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MainPanel {
    private final Scanner scan = new Scanner(System.in);
    private final String adminName = "Aladdin";
    private final String adminPassword = "2345";
    private static String usersTXT = "Users.txt";
    private Map<String, String> user = new HashMap<>();
    UserPanel userPanel;
    AdminPanel adminPanel;

    public MainPanel() {
    }

    public MainPanel(UserPanel userPanel, AdminPanel adminPanel) {
        this.userPanel = userPanel;
        this.adminPanel = adminPanel;
    }


    public void addAdmin() {
        user.put(adminName, adminPassword);
    }

    public void adminLogin() {

        System.out.print("Enter a username: ");
        String username = scan.nextLine();
        System.out.print("Enter a password: ");
        String password = scan.nextLine();
        if (username.equals(adminName) && password.equals(adminPassword)) {
            System.out.println("Login successful! Welcome, Aladdin.");
            adminPanel.showAdminPanel();
        } else {
            System.out.println("Username or password is incorrect");
        }
    }

    public boolean loginUsers() {
        int attempts = 3;
        Scanner scan = new Scanner(System.in);
        while (attempts > 0) {
            System.out.print("Enter a username: ");
            String username = scan.nextLine();
            System.out.print("Enter a password: ");
            String password = scan.nextLine();

            if (username.equals(adminName) && password.equals(adminPassword)) {
                return false;
            }
            loadFromFile();
            if (user.containsKey(username) && user.get(username).equals(password)) {
                System.out.println("Login successful! Welcome, " + username + '.');
                userPanel.showUserPanel();
                return true;
            } else {
                attempts--;
                System.out.println("Username or password is incorrect");
                if (attempts == 0) {
                    System.out.println("Too many failed attempts. Access denied.");
                    return false;
                }
            }
        }
        return false;
    }

    public void registerUsers() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a username: ");
        String username = scan.nextLine();
        System.out.print("Enter a password: ");
        String password = scan.nextLine();
        if (user.containsKey(username) && user.get(username).equals(password)) {
            System.out.println("There is a user with this name!");
        } else {
            user.put(username, password);
            saveToFile();
            showRegisterDisplay();
            System.out.println("Registration successful! You can now log in.");

        }
    }

    public void showRegisterDisplay() {
        try {
            while (true) {
                Scanner scan = new Scanner(System.in);
                System.out.println("1.Admin login.");
                System.out.println("2.Users login.");
                System.out.println("3.Register users.");
                System.out.println("4.Exit.");
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        addAdmin();
                        adminLogin();
                        break;
                    case 2:
                        loginUsers();
                        break;
                    case 3:
                        registerUsers();
                        break;
                    case 4:
                        System.out.println("Exiting the program...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct format!");
            scan.nextLine();
        }
    }


    public void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersTXT))) {
            for (Map.Entry<String, String> entry : user.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("The data was successfully written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(usersTXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    user.put(parts[0], parts[1]);
                }
            }
            System.out.println("Data was successfully loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}