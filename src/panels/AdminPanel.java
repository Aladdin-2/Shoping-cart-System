package panels;

import productClass.ProductManage;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scan = new Scanner(System.in);
    ProductManage productManage;


    public AdminPanel(ProductManage productManage) {
        this.productManage = productManage;
    }

    public void showAdminPanel() {

        while (true) {
            System.out.println("1.Add product to page.");
            System.out.println("2.Delete product by ID.");
            System.out.println("3.Show cart.");
            System.out.println("4.Change the price of the product.");
            System.out.println("5.Find by category.");
            System.out.println("6.Save to file.");
            System.out.println("7.Read from file..");
            System.out.println("9.Exit...");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    productManage.addProductToPage();
                    break;
                case 2:
                    productManage.removeProduct();
                    break;
                case 3:
                    productManage.showProduct();
                    break;
                case 4:
                    productManage.changeProductPrice();
                    break;
                case 5:
                    productManage.findProductByCategory();
                    break;
                case 6:
                    productManage.saveProductListToFile();
                    break;
                case 7:
                    productManage.readFromFile();
                    break;
                case 9:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
