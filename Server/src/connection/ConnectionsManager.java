package connection;

import gui.ControllerStartWindow;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Marcio on 28/06/2015.
 */
public class ConnectionsManager implements Runnable {
    ServerSocket socket = null;
    @Override
    public void run() {
        try {
            // Tenta criar um socket
            socket = new ServerSocket(12345);
            System.out.println("Server started! port: " + 12345);

            Socket client = null;
            while (true) {
                // Aguarda conexão do cliente
                try {
                    client = socket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("FAIL WHILE TRYING TO ACCEPT CONNECTION");
                }
                System.out.println("New connection! Client IP: " + client.getInetAddress().toString());
                // Cria objeto de comunicação entre cliente e servidor
                Communication clientCommunication = new Communication(client);
                // Cria e inicializa a thread do novo cliente
                Thread threadClient = new Thread(clientCommunication);
                threadClient.run();
            }
        } catch (IOException e) {
            System.err.println("ERROR TRYING TO CREATING SOCKET ON PORT: " + 12345);
            e.printStackTrace();
        }
    }
}
