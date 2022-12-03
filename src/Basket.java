import java.io.*;

public class Basket implements Serializable {
    protected int[] prices;
    protected String[] products;
    protected int[] countBasket;

    private static final long serialVersionUID = 1L;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        countBasket = new int[products.length];
    }

    void addToCart(int productNum, int amount) {
        countBasket[productNum] += amount;
    }

    void printCart() {
        System.out.println("Полный список продуктов в корзине: ");
        int sumProducts = 0;
        for (int i = 0; i < countBasket.length; i++) {
            sumProducts += countBasket[i] * prices[i];
            if (countBasket[i] != 0) {
                System.out.println(products[i] + " " + countBasket[i] + " " + "шт " + " " + prices[i] + "руб/шт " + countBasket[i] * prices[i] + " руб");
            }
        }
        System.out.println("Всего потрачено " + sumProducts + " руб");
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket1 = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            basket1 = (Basket) ois.readObject();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return basket1;
    }
}
