package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Marcio on 29/06/2015.
 */
public class Communication {
    private static Socket socket;

    public static void setSocket(Socket socket) {
        Communication.socket = socket;
    }

    public boolean registerUser(String[] fields) {
        PrintStream output = null;
        try {
            //Cria a Stream de saida de dados
            output = new PrintStream(socket.getOutputStream());
            // Envia o comando
            output.println("new user");
            System.out.println("enviou comando");
            // Envia os dados do usuário
            for (int i = 0; i < fields.length; i++){
                //Imprime uma linha para a stream de saída de dados
                output.println(fields[i]);
            }
            System.out.println("enviou dados");

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado e verifica qual foi a resposta do servidor
            String answer = input.readLine();
            System.out.println("recebeu resposta");
            if(answer.compareTo("user saved") == 0){
                input.close();
                output.close();
                return true;
            }
            else {
                input.close();
                output.close();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE TRYING TO REGISTER NEW USER");
            return false;
        }
    }

    public boolean loginUser(String[] fields) {
        PrintStream output = null;
        try {
            //Cria a Stream de saida de dados
            output = new PrintStream(socket.getOutputStream());
            // Envia o comando
            output.println("login user");
            System.out.println("enviou comando");
            // Envia os dados do usuário
            for (int i = 0; i < fields.length; i++){
                //Imprime uma linha para a stream de saída de dados
                output.println(fields[i]);
            }
            System.out.println("enviou dados");

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado e verifica qual foi a resposta do servidor
            String answer = input.readLine();
            System.out.println("recebeu resposta");
            if(answer.compareTo("ok") == 0){
                input.close();
                output.close();
                return true;
            }
            else {
                input.close();
                output.close();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE TRYING TO LOGIN USER");
            return false;
        }
    }
}
