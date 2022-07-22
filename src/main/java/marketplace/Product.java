package marketplace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Product {
    private final int id;
    private String name;
    private float amount;
    private float price;
    private static List<Product> listOfProducts = new ArrayList<>();

    public Product(int id, String name, float amount, float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        listOfProducts.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public static Product getProduct(int id) {
        for (Product product : listOfProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static void displayListOfProduct() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of products\n");
        for (Product product : listOfProducts) {
            sb.append("\t" + product.toString() + "\n");
        }
        System.out.println(sb);
    }

    public void expenses(float amount) {
        this.amount = this.amount - amount;
    }

    public static void displayUsersThatBoughtProduct(int id) throws AppException {
        Product product = getProduct(id);
        if (product == null){
            throw new AppException("Product with id " + id + " was not found.");
        }
        List<User> users = User.getListOfUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("List of users that bought " + product.getName() + ":\n");
        boolean boughtProduct = false;
        for (User user : users) {
            Map<Product, Float> userProducts = user.getUserProducts();
            for (Map.Entry<Product, Float> userProduct : userProducts.entrySet()) {
                if (userProduct.getKey().equals(product)) {
                    sb.append("\t" + user.getFirstName() + " "
                            + user.getLastName() + " has "
                            + userProduct.getValue() + " " + product.getName() + "\n");
                    boughtProduct = true;
                }
            }
        }
        if (boughtProduct){
            System.out.println(sb);
        }

    }

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", amount = " + amount +
                ", price = " + price;
    }
}
