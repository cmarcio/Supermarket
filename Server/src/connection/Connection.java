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
    }
}
