package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Marcio on 28/06/2015.
 */
public class ConnectionsManager implements Runnable {
    private int port = 12345;

    @Override
    public void run() {
        try {
            // Tenta criar um socket
            ServerSocket server = null;
            server = new ServerSocket(port);
            System.out.println("Server started! port: " + port);

            Socket client = null;
            for(;;) {
                // Aguarda conexão do cliente
                try {
                    client = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("New connection! Client IP: " + client.getInetAddress().toString());
                // Cria objeto de comunicação entre cliente e servidor
                Connection clientConnection = new Connection(client);
                // Cria e inicializa a thread do novo cliente
                Thread threadClient = new Thread(clientConnection);
                threadClient.run();
            }

        } catch (IOException e) {
            System.err.println("Erro ao criar socket na porta: " + port);
            //e.printStackTrace();
            // Muda a porta
            if (port >= 0 && port < 65535)
                port ++;
            else port = 0;
            // Tenta de novo
            this.run();
        }
    }
}
