package gui;

import connection.ConnectionsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    Thread server = null;

    public Controller(){
        server = new Thread(new ConnectionsManager());
        server.start();
    }
}