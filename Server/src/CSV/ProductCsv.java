package csv;

import gui.ControllerRegisterWindow;
import market.Product;
import market.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public synchronized void updateFile(ArrayList<Product> list) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(getCsv());
            PrintWriter output = new PrintWriter(fileWriter);

            for(int i = 0; i < list.size(); i++){
                Product product = list.get(i);
                // Escreve os dados do livro no arquivo
                output.printf("%s,%s,%s,%s,%s\n", product.getName(), product.getPrice(), product.getVendor(), product.getQuantity(), product.getExpirationDay());
            }

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR UPDATING PRODUCTS FILE!");
            e.printStackTrace();
        }
    }
}
