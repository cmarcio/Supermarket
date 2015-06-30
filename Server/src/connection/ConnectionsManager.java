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
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started! port: " + port);


        } catch (IOException e) {
            System.err.println("Erro ao criar socket na porta especificada");
            e.printStackTrace();
            // Muda a porta
            if (port >= 0 && port < 65535)
                port ++;
            else port = 0;
            // Tenta de novo
            this.run();
        }

        // Cria uma thread que fica apenas aceitando conexões
        final ServerSocket finalServer = server;
        new Thread(()->{
            while (true) {
                // Aguarda conexão do cliente
                Socket client = null;
                try {
                    client = finalServer.accept();
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
        });
    }
}
