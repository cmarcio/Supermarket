package connection;

import market.Product;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
                return true;
            }
            else {
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
                return true;
            }
            else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE TRYING TO LOGIN USER");
            return false;
        }
    }

    public ArrayList<Product> getInventory() {
        PrintStream output = null;
        try {
            //Cria a Stream de saida de dados
            output = new PrintStream(socket.getOutputStream());
            // Envia o comando
            output.println("get inventory");
            System.out.println("enviou comando");

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado e verifica qual o numero de objetos q serão recebidos
            Integer size = Integer.parseInt(input.readLine());
            System.out.println("Numero de elementos: " + size);

            // Le todos os objetos e coloca em uma lista
            ArrayList<Product> products = new ArrayList<Product>();
            for(int i = 0; i < size; i++) {
                String[] fields = new String[5];
                // Le os dados do produto
                fields[0] = input.readLine();
                fields[1] = input.readLine();
                fields[2] = input.readLine();
                fields[3] = input.readLine();
                fields[4] = input.readLine();
                // Cria novo objeto
                Product product = new Product(fields);
                // Coloca na lista
                products.add(product);
            }
            return products;

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE TRYING TO LOGIN USER");
        }
        return null;
    }

    public boolean buy(String name) {
        PrintStream output = null;
        try {
            //Cria a Stream de saida de dados
            output = new PrintStream(socket.getOutputStream());
            // Envia o comando
            output.println("buy");
            System.out.println("enviou comando");
            // Envia os dados do usuário
            output.println(name);
            System.out.println("enviou dados");

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado e verifica qual foi a resposta do servidor
            String answer = input.readLine();
            System.out.println("recebeu resposta");
            if(answer.compareTo("ok") == 0){
                return true;
            }
            else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR WHILE TRYING TO BUY");
            return false;
        }
    }
}
