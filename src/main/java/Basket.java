import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Basket implements Serializable {
    protected int[] prices;
    protected String[] products;
    protected int[] countBasket;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        countBasket = new int[products.length];
    }

    public Basket(String[] products, int[] prices, int[] countBasket) {
        this.products = products;
        this.prices = prices;
        this.countBasket = countBasket;
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

    public static Basket loadFromTxtFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String[] products = reader.readLine().split(" ");

            String[] pricesStr = reader.readLine().split(" ");
            int[] prices = new int[pricesStr.length];
            for (int i = 0; i < pricesStr.length; i++) {
                prices[i] = Integer.parseInt(pricesStr[i]);
            }
            Basket basket = new Basket(products, prices);
            String[] buy = reader.readLine().split(" ");
            for (int i = 0; i < buy.length; i++) {
                basket.countBasket[i] = Integer.parseInt(buy[i]);
            }
            return new Basket(products, prices, basket.countBasket);
        }
    }

    public void saveJSON(File file) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(this));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static Basket loadFromJSON(File file) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (FileReader reader = new FileReader(file)) {
            Basket basket = gson.fromJson(reader, Basket.class);
            return basket;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

