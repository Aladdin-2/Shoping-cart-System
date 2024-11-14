package cartManage;

import productClass.Product;
import productClass.ProductManage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductCart {
    private final Scanner scan = new Scanner(System.in);
    List<Product> selectedProduct = new ArrayList<>();
    ProductManage product;

    public ProductCart(ProductManage productManage) {
        this.product = productManage;
    }

    public void addProductToCart() {
        System.out.println("                                Products...");
        if (product.getProducts().isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product product : product.getProducts()) {
            System.out.println(product);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Write the name of the product you want to add!");
        String productName = scan.nextLine();
        Product productToAdd = product.findProductByName(productName);
        if (productToAdd != null) {
            selectedProduct.add(productToAdd);
            System.out.println(productName + " added to cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void showCartItem() {
        if (selectedProduct.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        for (Product cartProduct : selectedProduct) {
            System.out.println(cartProduct.toString());
        }
    }

    public void showTotalPriceInTheCart() {
        double totalPrice = 0;
        for (Product products : selectedProduct) {
            totalPrice += products.getProductPrice();
        }
        System.out.printf("Total price of the products in the basket: %.2f\n", totalPrice);
    }

    public void searchProductByPriceRange() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the minimum price: ");
        double minPrice = scan.nextDouble();
        System.out.println("Enter the maximum price: ");
        double maxPrice = scan.nextDouble();
        scan.nextLine();
        for (Product products : product.getProducts()) {
            if (products.getProductPrice() >= minPrice && maxPrice >= products.getProductPrice()) {
                System.out.println(products);
            }
        }
    }

    public void findProductByCategory() {
        try {
            System.out.println("Write the category you want to find: ");
            String category = scan.nextLine();

            if (product != null && product.getCategorizedProducts() != null) {
                if (product.getCategorizedProducts().containsKey(category)) {
                    System.out.println("Products in category '" + category + "': " + product.getCategorizedProducts().get(category));
                } else {
                    System.out.println("Category '" + category + "' not found.");
                }
            } else {
                System.out.println("No categorized products available.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching for the product.");
        }
    }

    public void checkProductAvailabilityInTheList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the product name to check availability: ");
        String name = scan.nextLine();

        for (Product product : product.getProducts()) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                System.out.println("Product is available. Quantity: " + product.getProductCount());
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void checkProductAvailabilityByNameFromCart() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the product name to check availability in the cart: ");
        String name = scan.nextLine();
        for (Product product : selectedProduct) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                System.out.println("Product is available . Quantity: " + product.getProductCount());
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void removeFromCart() {
        Scanner scan = new Scanner(System.in);
        if (selectedProduct.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        showCartItem();
        System.out.print("Write the product ID you want to delete! : ");
        int productID = scan.nextInt();
        for (Product product : selectedProduct) {
            if (product.getProductID() == productID) {
                selectedProduct.remove(product);
                System.out.println("Product removed from cart!");
                break;
            } else {
                System.out.println("This product was not found!");
                return;
            }
        }
    }

    public void saveProductListToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productList.txt"))) {
            for (Product product : selectedProduct) {
                writer.write(product.toString());
                writer.newLine();
            }
            System.out.println("Product list saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saved the list.");
        }
    }
}

