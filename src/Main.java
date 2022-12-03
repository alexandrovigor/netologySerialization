
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] products = {"Хлеб", "Яблоки", "Молоко", "Пиво", "Яйцо"};
        int[] prices = {100, 200, 300, 250, 45};

        Basket basket1 = new Basket(products, prices);

        File file = new File("basket.bin");

        if (file.exists()) {
            basket1 = Basket.loadFromBinFile(file);
            basket1.printCart();
        } else {
            System.out.println("Пока корзина пустая");
        }
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + "." + products[i] + " " + prices[i] + "руб/шт");
        }
        int SIZE = products.length;
        int[] countBasketAnyProducts = new int[SIZE];
        int[] countBasket = new int[SIZE];
        int sumProducts = 0;

        System.out.println("Выберите товар и количество или введите `end`");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (!"end".equals(input)) {
                try {
                    String[] currentBasket = input.split(" ");
                    int productNumber = Integer.parseInt(currentBasket[0]) - 1;
                    if (currentBasket.length != 2) {
                        System.out.println("Введите код товара и его количество через пробел ещё раз");
                        continue;
                    }
                    int productCount = Integer.parseInt(currentBasket[1]);
                    if (productNumber + 1 < 0 || productCount <= 0) {
                        System.out.println("Значения должны быть положительными");
                        continue;
                    }
                    try {
                        countBasketAnyProducts[productNumber] += productCount;
                        countBasket[productNumber] = productCount;
                        int sum = prices[productNumber] * countBasket[productNumber];
                        sumProducts += sum;
                        basket1.addToCart(productNumber, productCount);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Товара под таким номером не существует");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели текст " + "'" + input + "'" + " ,а нужно цифровые значения");
                }
                continue;
            }
            System.out.println("Ваша корзина в эту сессию покупок:");
            for (int x = 0; x < products.length; x++) {
                if ((countBasket[x]) != 0) {
                    System.out.println(products[x] + " " + countBasketAnyProducts[x] + " шт," + prices[x] + " руб/шт," + prices[x] * countBasketAnyProducts[x] + "");
                }
            }
            System.out.println("Итого " + sumProducts + " руб");
            break;
        }
        basket1.saveBin(file);
        basket1.printCart();
    }
}