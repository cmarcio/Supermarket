package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Abre a janela do programa
        Parent root = FXMLLoader.load(getClass().getResource("Principal_server.fxml"));
        primaryStage.setTitle("Servidor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Iniciliza a interface
        launch(args);
    }
}
