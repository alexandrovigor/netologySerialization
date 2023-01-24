import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private static List<String[]> list = new ArrayList<>();

    public void log(int productNum, int amount) {
        String[] nextBasket = {Integer.toString(productNum + 1), Integer.toString(amount)};
        list.add(nextBasket);
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile))) {
            writer.writeNext(new String[]{"productNum", "amount"});
            for (String[] nextBasket : list) {
                writer.writeNext(nextBasket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
