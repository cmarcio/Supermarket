package csv;

import java.io.*;

/**
 * Created by Marcio on 18/06/2015.
 */
public abstract class FileCsv {
    private File csv;

    public FileCsv(String fileName) {
        // Verifica se o diretório de dados já foi criado
        File folder = new File("data");
        if (!folder.exists())
            folder.mkdir(); // Cria o diretório caso não tenha sido criado

        // Cria o arquivo dentro do diretório de dados
        csv = new File(folder, fileName);
    }

    public synchronized void store(String[] fields) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(csv, true);
            PrintWriter output = new PrintWriter(fileWriter);

            // Escreve os dados do usuário no arquivo
            for (int i = 0; i < fields.length -1; i++) {
                output.printf("%s,", fields[i]);
            }
            output.printf("\n");

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING FILE!");
            e.printStackTrace();
        }
    }
}
