package productClass;

import java.util.Random;

public class Product {
    private String category;
    private String productName;
    private double productPrice;
    private int productID;
    private int productCount;

    public Product() {

    }

    public Product(String category, String productName, double productPrice, int productCount) {
        this.category = category;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productID = new Random().nextInt(100, 1000);
        this.productCount = productCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;

    }

    public int getProductID() {
        return productID;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Product { " +
                "Product ID=" + productID +
                " Category= " + category + "||" +
                ", product name= " + productName +
                ", product price= " + productPrice +
                ", productCount= " + productCount +
                '}';
    }
}
