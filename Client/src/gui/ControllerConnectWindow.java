package gui;

import connection.Communication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Marcio on 30/06/2015.
 */
public class ControllerConnectWindow extends ControllerStartWindow{
    @FXML private Button btnConnect;
    @FXML private TextField txtIp;
    @FXML private TextField txtPort;

    @FXML void handleConnectButton(ActionEvent event) {
        // Verifica se os campos foram preenchidos
        if(txtIp.getText().isEmpty() || txtPort.getText().isEmpty()) {
            showMessage("Aviso", "Campos vazios", "Preencha corretamente todos os campos para continuar", Alert.AlertType.WARNING);
        }
        // Tenta fazer a conexão com o servidor
        else {
            int port = Integer.parseInt(txtPort.getText());
            // Tenta conectar-se ao servidor
            try {
                Socket socket = new Socket(txtIp.getText(), port);
                // Salva o socket na classe de conexão
                Communication.setSocket(socket);
                // Abre a janela inicial
                loadWindow(btnConnect, "Inicio_cliente.fxml");
            } catch (IOException e) {   // Exibe mensagem de erro na conexão
                e.printStackTrace();
                showMessage("Erro", "Falha na conexão", "Não foi possível conectar ao servidor", Alert.AlertType.ERROR);
            }
        }
    }
}
