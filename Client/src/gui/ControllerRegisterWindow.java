package gui;

import connection.Communication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Marcio on 18/06/2015.
 */
public class ControllerRegisterWindow extends ControllerStartWindow {
    @FXML private TextField txtName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtId;
    @FXML private TextField txtPassword;
    @FXML private Button btnRegister;
    @FXML private Button btnCancel;

    @FXML private void handleButtonAction(ActionEvent event) {
        // Se o usuário clicou em cancelar
        if (event.getSource() == btnCancel) {
            loadWindow(btnCancel, "Inicio_cliente.fxml");
        }
        // Se o usuário clicou em cadastrar e todos os campos estão corretos
        else if (event.getSource() == btnRegister && verifyTextFields()) {
            // Salva os campos
            String[] fields = {txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtEmail.getText(), txtId.getText(), txtPassword.getText()};
            // Cria um objeto de comunicação com o servidor
            Communication serverCommunication = new Communication();
            // Se o precesso de cadastro retornou falha
            if( !serverCommunication.registerUser(fields) )
                showMessage("Erro", "Falha ao registrar usuário", "Usuário já existe ou falha de conexão", Alert.AlertType.ERROR);
            // Se o processo de cadastro retornou exito
            else
                showMessage("Sucesso", null, "Novo usuário registrado com sucesso", Alert.AlertType.INFORMATION);
            // Volta pra tela inicial
            loadWindow(btnRegister, "Inicio_cliente.fxml");
        }
        // Se o usuário tentou cadastrar sem preencher os campos corretamente
        else {
            showMessage("Aviso", null, "Verifique se todos os campos foram preenchidos corretamente e tente novamente", Alert.AlertType.WARNING);
        }
    }

    private boolean verifyTextFields(){
        // Verifica se todos os campos foram preenchidos
        if (txtName.getText().isEmpty() || txtAddress.getText().isEmpty()
                || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtId.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            showMessage("Aviso", "Campos Incorretos", "Você deve preencher antes de continuar", Alert.AlertType.WARNING);
            return false;
        }
        // Verifica se o número de telefone é válido
        else if (!isValidNumber(txtPhone)) {
            showMessage("Aviso", "Telefone Inválido", "O campo Telefone deve conter apenas numeros", Alert.AlertType.WARNING);
            return false;
        }
        else return true;
    }

    // Verifica se um campo possui apenas números
    public boolean isValidNumber(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if (! Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }
}
