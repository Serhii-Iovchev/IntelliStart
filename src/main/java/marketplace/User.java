package marketplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final int id;
    private String firstName;
    private String lastName;
    private float amountOfMoney;
    private final static List<User> listOfUsers = new ArrayList<>();
    private final Map<Product, Float> userProducts = new HashMap<>();

    public User(int id, String firstName, String lastName, float amountOfMoney) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
        listOfUsers.add(this);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public Map<Product, Float> getUserProducts() {
        return userProducts;
    }

    public static List<User> getListOfUsers() {
        return listOfUsers;
    }

    public static void displayListOfUsers() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of users\n");
        for (User user : listOfUsers) {
            sb.append("\t" + user.toString() + "\n");
        }
        System.out.println(sb);
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", first name = " + firstName +
                ", last name = " + lastName +
                ", amount of money = " + amountOfMoney;
    }

    public static void buyProduct(int userId, int productId, float amountOfProduct) throws AppException {
        User user = getUser(userId);
        if (user == null) {
            throw new AppException("User with id " + userId + " was not found. Enter another id.");
        }
        Product product = Product.getProduct(productId);
        if (product == null) {
            throw new AppException("Product with id " + productId + " was not found. Enter another id.");
        }
        float balance = user.getAmountOfMoney() - product.getPrice() * amountOfProduct;
        if (balance < 0) {
            throw new AppException("Not enough money in the account. " + -balance + " more needed");
        }
        float restOfProduct = product.getAmount() - amountOfProduct;
        if (restOfProduct < 0) {
            throw new AppException("Not enough amount of product. " + -restOfProduct + " more needed");
        }
        System.out.println(user.getFirstName() + " " + user.getLastName() +
                " bought " + amountOfProduct + " of " + product.getName() + "\n");

        user.amountOfMoney = balance;
        product.expenses(amountOfProduct);
        user.addProduct(product, amountOfProduct);

    }

    private static User getUser(int id) {
        for (User user : listOfUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private void addProduct(Product product, float amount) {
        if (!userProducts.containsKey(product)) {
            userProducts.put(product, amount);
        } else {
            for (Map.Entry<Product, Float> userProduct : userProducts.entrySet()) {
                if (userProduct.getKey().equals(product)) {
                    userProduct.setValue(userProduct.getValue() + amount);
                }
            }
        }
    }

    public static void displayUserBasket(int id) throws AppException {
        User user = getUser(id);
        if (user == null) {
            throw new AppException("User with id " + id + " was not found.");
        }
        Map<Product, Float> products = user.getUserProducts();
        if (products.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The basket of " + user.getFirstName() + " " + user.getLastName() + ":\n");
        for (Map.Entry<Product, Float> product : products.entrySet()) {
            sb.append("\t" + product.getKey().getName());
            sb.append(" - " + product.getValue() + "\n");
        }
        sb.append("His amount of money " + user.getAmountOfMoney() + "\n");
        System.out.println(sb);

    }
}
