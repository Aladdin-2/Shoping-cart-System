package productClass;

import java.io.*;
import java.util.*;

public class ProductManage {
    List<Product> productListForPage = new ArrayList<>();

    Map<String, List<Product>> categorizedProducts = new HashMap<>();


    public ProductManage() {
    }

    public List<Product> getProducts() {
        return productListForPage;
    }

    public Map<String, List<Product>> getCategorizedProducts() {
        return categorizedProducts;
    }

    public void addProductToPage() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Write the product category (type 'exit' to stop!): ");
                String category = scan.nextLine();
                if (category.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.print("Write the product name: ");
                String name = scan.nextLine();
                System.out.print("Write the product price: ");
                double price = scan.nextDouble();
                scan.nextLine();
                System.out.print("Write the product count: ");
                int count = scan.nextInt();
                scan.nextLine();
                Product newProduct = new Product(category, name, price, count);
                productListForPage.add(newProduct);
                for (Product product : productListForPage) {
                    categorizedProducts.
                            computeIfAbsent(product.getCategory(), k -> new ArrayList<>())
                            .add(product);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct format!");
                scan.nextLine();
            }
        }
    }

    public void showProduct() {
        for (Product product : productListForPage) {
            System.out.println(product);
        }
    }

    public Product findProductByName(String name) {
        for (Product product : productListForPage) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public void findProductByCategory() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write the category you want to find! ");
        String category = scan.nextLine();
        if (categorizedProducts != null) {
            System.out.println("Products in category '" + category + "': " + categorizedProducts.get(category));
        } else {
            System.out.println("Category '" + category + "' not found.");
        }
    }

    public boolean removeProduct() {
        Scanner scan = new Scanner(System.in);
        if (productListForPage.isEmpty()) {
            System.out.println("List is empty");
            return false;
        }
        System.out.println("Write the product ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        for (Product product : productListForPage) {
            if (id == product.getProductID()) {
                productListForPage.remove(product);
                System.out.println("This product has been deleted!");
                return true;
            }
            if (id != product.getProductID()) {
                System.out.println("This product was not found!");
            }
        }
        return false;
    }

    public void changeProductPrice() {
        if (productListForPage.isEmpty()) {
            return;
        }
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter product İD.");
            int id = scan.nextInt();
            for (Product product : productListForPage) {
                if (product.getProductID() == id) {
                    System.out.println("Enter the new price.");
                    double newPrice = scan.nextDouble();
                    scan.nextLine();
                    product.setProductPrice(newPrice);
                    System.out.println("Product price updated successfully.");
                }
            }
        }
    }

    public void saveProductListToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productList.txt"))) {
            for (Product product : productListForPage) {
                writer.write(product.toString());
                writer.newLine();
            }
            System.out.println("Product list saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saved the list.");
        }
    }


    public List<Product> readFromFile() {
        try (BufferedReader rider = new BufferedReader(new FileReader("productList.txt"))) {
            String line;
            while ((line = rider.readLine()) != null) {
                System.out.println(line);

            }
        } catch (IOException e) {
            System.out.println("Error reading from file:" + e.getMessage());
        }
        return productListForPage;
    }


}

