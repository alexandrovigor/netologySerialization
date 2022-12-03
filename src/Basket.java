import java.io.*;

public class Basket {
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

    public void saveTxt(File file) throws IOException {
        try (PrintWriter quantity = new PrintWriter(file)) {
            for (String product : products) {
                quantity.print(product + " ");
            }
            quantity.print("\n");
            for (int price : prices) {
                quantity.print(price + " ");
            }
            quantity.print("\n");
            for (int countProduct : countBasket) {
                quantity.print(countProduct + " ");
            }
        }
    }
}
