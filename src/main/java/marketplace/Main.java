package marketplace;

public class Main {
    public static void main(String[] args) throws AppException {
        Product firstProduct = new Product(1, "apple", 55.3f, 2.5f);
        Product secondProduct = new Product(2, "pear", 79.9f, 3.1f);
        Product thirdProduct = new Product(3, "banana", 33.1f, 5.7f);

        User firstUser = new User(1, "John", "Parker", 2000);
        User secondUser = new User(2, "Will", "Stone", 1700);
        User thirdUser = new User(3, "Petro", "Bird", 5000);

        User.displayListOfUsers();
        Product.displayListOfProduct();

        User.buyProduct(2, 2, 5);
        User.buyProduct(2, 1, 7);
        User.buyProduct(2, 1, 7);
        User.buyProduct(1, 2, 7);
        User.buyProduct(1, 2, 7);

        User.displayUserBasket(2);
        User.displayUserBasket(1);
        User.displayUserBasket(3);

        Product.displayUsersThatBoughtProduct(2);
        Product.displayUsersThatBoughtProduct(3);
    }
}