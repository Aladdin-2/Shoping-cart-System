package panels;

import cartManage.ProductCart;

import java.util.Scanner;

public class UserPanel {
    private final Scanner scan = new Scanner(System.in);

    ProductCart productCart;

    public UserPanel(ProductCart productCart) {
        this.productCart = productCart;
    }

    public void showUserPanel() {
        while (true) {
            System.out.println("1.Add product.");
            System.out.println("2.Show cart.");
            System.out.println("3.Delete product by ID.");
            System.out.println("4.Total price in the cart.");
            System.out.println("5.Search product by price range.");
            System.out.println("6.Search for product availability in the list.");
            System.out.println("7.Search for product availability in the cart.");
            System.out.println("8.Search for product by category.");
            System.out.println("10..................");
            System.out.println("12.Exit...");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    productCart.addProductToCart();
                    break;
                case 2:
                    productCart.showCartItem();
                    break;
                case 3:
                    productCart.removeFromCart();
                    break;
                case 4:
                    productCart.showTotalPriceInTheCart();
                    break;
                case 5:
                    productCart.searchProductByPriceRange();
                    break;
                case 6:
                    productCart.checkProductAvailabilityInTheList();
                    break;
                case 7:
                    productCart.checkProductAvailabilityByNameFromCart();
                    break;
                case 8:
                    productCart.findProductByCategory();
                    break;
                case 12:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

}
