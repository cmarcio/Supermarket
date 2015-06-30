package connection;

import java.net.Socket;

/**
 * Created by Marcio on 29/06/2015.
 */
public class Connection {
    private static Socket socket;

    public static void setSocket(Socket socket) {
        Connection.socket = socket;
    }
}
