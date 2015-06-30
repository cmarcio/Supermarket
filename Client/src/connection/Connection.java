package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Marcio on 29/06/2015.
 */
public class Connection {
    private static Socket socket;

    public static void setSocket(Socket socket) {
        Connection.socket = socket;
    }

    public boolean registerUser(String[] fields) {
        //Cria a Stream de saida de dados
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(socket.getOutputStream());
            // Envia o comando
            printStream.println("new user");
            // Envia os dados do usuário
            for (int i = 0; i < fields.length; i++){
                //Imprime uma linha para a stream de saída de dados
                printStream.println(fields[i]);
            }

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado e verifica qual foi a resposta do servidor
            if(input.readLine().compareTo("user saved") == 0){
                input.close();
                printStream.close();
                return true;
            }
            else {
                input.close();
                printStream.close();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
