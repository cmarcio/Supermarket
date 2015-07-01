package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Marcio on 30/06/2015.
 */
public class Receiver {
    private Socket socket;
    private String[] userFields;
    private boolean user = false;
    private boolean loginRequest = false;

    public Receiver(Socket socket) {
        this.socket = socket;
        userFields = new String[6];
    }

    public void read() {
        //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Aguarda o recebimento de um comando
            String command = input.readLine();
            System.out.println("recebeu comando = " + command);

            // Verifica qual é o comando
            if (command.compareTo("new user") == 0) {
                // Le os dados fornecidos
                for (int i = 0; i < userFields.length; i++)
                    userFields[i] = input.readLine();
                // Seta que todos os dados foram lidos
                user = true;
                System.out.println("Leu");
            }
            else if (command.compareTo("login user") == 0) {
                // Le os dados fornecidos
                for (int i = 0; i < 2; i++)
                    userFields[i] = input.readLine();
                // Seta que há um pedido de logim
                loginRequest = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE PROCESSING THE COMMAND");
        }
    }

    public boolean hasUser() {
        return user;
    }

    public String[] getUserFields() {
        user = false;
        return userFields;
    }

    public boolean loginRequest() {
        return loginRequest;
    }
}