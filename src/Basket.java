import java.io.*;

public class Basket implements Serializable {
    protected static int[] prices;
    protected static String[] products;
    protected static int sumProducts;
    protected static int[] countBasket;

    Basket(String[] products, int[] prices) {
        Basket.products = products;
        Basket.prices = prices;
        countBasket = new int[products.length];
    }

    //  static void loadFromTxtFile(File file) throws IOException {
    //      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

    //         String[] buy = reader.readLine().split(" ");
    //         for (int i = 0; i < buy.length; i++) {
    //             countBasket[i] = Integer.parseInt(buy[i]);
    //        }
    //       sumProducts = Integer.parseInt(reader.readLine());
    //    }
    //    new Basket(products, prices);
    // }
    void addToCart(int productNum, int amount) {
        sumProducts += prices[productNum] * amount;
        countBasket[productNum] += amount;
    }

    void printCart() {
        System.out.println("Полный список продуктов в корзине: ");

        for (int j = 0; j < countBasket.length; j++) {
            if (countBasket[j] != 0) {
                System.out.println(products[j] + " " + countBasket[j] + " " + "шт " + " " + prices[j] + "руб/шт "
                        + countBasket[j] * prices[j] + " руб");
            }
        }
        System.out.println("Всего потрачено " + sumProducts + " руб");
    }

    //  public void saveTxt(File file) throws IOException {
    //     try (PrintWriter quantity = new PrintWriter(file)) {
    //         for (int countProduct : countBasket) {
    //             quantity.print(countProduct + " ");
    //         }
    //         quantity.print("\n");
    //         quantity.print(sumProducts);
    //         quantity.print("\n");
    //     }
    // }
    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(products);
            oos.writeObject(prices);
            oos.writeObject(countBasket);
            oos.writeObject(sumProducts);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            products = (String[]) ois.readObject();
            prices = (int[]) ois.readObject();
            countBasket = (int[]) ois.readObject();
            sumProducts = (int) ois.readObject();
        } catch (EOFException ignored) {
        }
    }
}


