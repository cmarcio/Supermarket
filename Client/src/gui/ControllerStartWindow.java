package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerStartWindow {
    @FXML private Button btnLogin;
    @FXML private Button btnRegister;

    // Abre uma janela de acordo com o botão clicado
    @FXML void handleStartWindow(ActionEvent event) {
        Button source = (Button) event.getSource();
        if (source == btnLogin) {
            loadWindow(source, "Login_cliente.fxml");
        }
        else {
            loadWindow(source, "CadastrarUsuario_cliente.fxml");
        }
    }

    // Exibe uma mensagem contendo as strings passadas como parâmetro, e o tipo especificado
    public void showMessage(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Carrega uma janela
    public void loadWindow(Button btn, String fxml) {
        Stage stage=(Stage) btn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("ERROR LOADING WINDOW!");
            e.printStackTrace();
        }
    }
}
