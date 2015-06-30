package gui;

import connection.ConnectionsManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Abre a janela do programa
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // Inicializa a thread de conexões
        Thread threadConnections = new Thread(new ConnectionsManager());
        threadConnections.run();

        // Iniciliza a interface
        launch(args);
    }
}
