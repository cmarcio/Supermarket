package csv;

import market.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Marcio on 30/06/2015.
 */
public class UserCsv extends FileCsv {
    public UserCsv(String fileName) {
        super(fileName);
    }

    @Override
    public synchronized ArrayList<User> readAll() {
        ArrayList<User> list = new ArrayList<User>();

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
                    User user = new User(fields);
                    // Adiciona o objeto a list
                    list.add(user);
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
