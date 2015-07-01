package csv;

import market.Product;
import market.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marcio on 30/06/2015.
 */
public class ProductCsv extends FileCsv {
    public ProductCsv(String fileName) {
        super(fileName);
    }

    @Override
    public synchronized ArrayList<Product> readAll() {
        ArrayList<Product> list = new ArrayList<Product>();

        FileReader reader = null;
        String line;
        File file = getCsv();

        if (file.exists()) {
            try {
                reader = new FileReader(file);
                BufferedReader input = new BufferedReader(reader);

                // Le linha por linha do arquivo até o fim
                while ((line = input.readLine()) != null) {
                    // Quebra a linha em partes
                    String[] fields = line.split(",");
                    // Cria um novo objeto e adiciona os valores aos campos
                    Product product = new Product(fields);
                    // Adiciona o objeto a list
                    list.add(product);
                }

                input.close();
                reader.close();
                return list;

            } catch (IOException e) {
                System.err.println("ERROR WHILE READING FILE!");
                e.printStackTrace();
            }
        }
        return null;
    }
}
