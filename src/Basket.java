import java.io.*;

import static java.lang.Long.SIZE;

public class Basket {
    protected static int[] prices;
    protected static String[] products;
    protected static int sumProducts;
    protected static int[] countBasket = new int[SIZE];

    Basket(String[] products, int[] prices) {
        Basket.products = products;
        Basket.prices = prices;
    }

    static void loadFromTxtFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String[] buy = reader.readLine().split(" ");
            for (int i = 0; i < buy.length; i++) {
                countBasket[i] = Integer.parseInt(buy[i]);
            }
            sumProducts = Integer.parseInt(reader.readLine());
        }
        new Basket(products, prices);

    }

    void addToCart(int productNum, int amount) {
        sumProducts += prices[productNum] * amount;
        countBasket[productNum] += amount;
    }

    void printCart() {

        System.out.println("Полный список продуктов в корзине: ");

        for (int j = 0; j < countBasket.length; j++) {
            if (countBasket[j] != 0) {
                System.out.println(products[j] + " " + countBasket[j] + " " + "шт " + " " + prices[j] + "руб/шт " + countBasket[j] * prices[j] + " руб");
            }
        }
        System.out.println("Всего потрачено " + sumProducts + " руб");
    }
    public void saveTxt(File file) throws IOException {
        try (PrintWriter quantity = new PrintWriter(file)) {
            for (int countProduct : countBasket) {
                quantity.print(countProduct + " ");
            }
            quantity.print("\n");
            quantity.print(sumProducts);
            quantity.print("\n");
        }
    }

}
