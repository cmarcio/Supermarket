package gui;

import connection.Communication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Marcio on 01/07/2015.
 */
public class ControllerLoginWindow extends ControllerStartWindow {
    @FXML private Button btnCancel;
    @FXML private Button btnOk;
    @FXML private TextField txtId;
    @FXML private PasswordField pwfPassword;

    @FXML private void handleButtonAction(ActionEvent event) {
        Button source = (Button) event.getSource();
        // Se o usu�rio clicou em cancelar
        if(source == btnCancel)
            loadWindow(btnCancel, "Inicio_cliente.fxml");
        // Se o usu�rio clicou em logar e os campos foram preenchidos
        else if (source == btnOk && !txtId.getText().isEmpty() && !pwfPassword.getText().isEmpty()){
            // Salva os campos
            String[] fields = {txtId.getText(), pwfPassword.getText()};
            // Cria um novo objeto de comunica��o com o servidor
            Communication serverCommunication = new Communication();
            // Tenta logar
            if(serverCommunication.loginUser(fields)) {
                // Se o usu�rio puder logar
                showMessage("Logado", "Bem Vindo ao Ades Market", txtId.getText(), Alert.AlertType.INFORMATION);
                // Carrega a tela de principal
                loadWindow(btnOk, "Principal_cliente.fxml");
            }
            else{
                // Se o usu�rio n�o puder logar
                showMessage("Erro", "Acesso negado", "Usu�rio ou senha incorreta", Alert.AlertType.ERROR);
            }
        }
        else {
            // Se o usu�rio n�o preencheu todos os campos
            showMessage("Aviso", null, "Verifique se todos os campos foram preenchidos corretamente e tente novamente", Alert.AlertType.WARNING);
        }
    }
}
