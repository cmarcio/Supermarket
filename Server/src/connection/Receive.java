package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Marcio on 30/06/2015.
 */
public class Receive implements Runnable {
    private Socket socket;
    public ArrayList<String> line;

    public Receive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Aguarda por algum dado salva na lista
            while (true){
                line.add(input.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNextLine(){
        return line.iterator().hasNext();
    }

    public String nextLine(){
        return line.iterator().next();
    }

    public int numberOfLines(){
        return line.size();
    }

    public void remove(String str) {
        line.remove(str);
    }
}
