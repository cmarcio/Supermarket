package connection;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Marcio on 30/06/2015.
 */
public class Sender {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    void sendString(String str) {
        //Cria a Stream de saida de dados
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(socket.getOutputStream());
            // Envia o comando
            printStream.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
