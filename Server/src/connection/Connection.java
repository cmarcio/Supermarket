package connection;

import java.net.Socket;

/**
 * Created by Marcio on 28/06/2015.
 */
public class Connection implements Runnable{
    private Socket client;
    public Connection(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        Receive receive = new Receive(client);

        while (true) {
            if (receive.hasNextLine()){
                String command = receive.nextLine();
                if (command.compareTo("new user") == 0 )

            }
        }
    }
}
