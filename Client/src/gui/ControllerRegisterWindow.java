package gui;

import csv.FileCSV;
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
    @FXML private TextField txtLastName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtId;
    @FXML private TextField txtPassword;
    @FXML private Button btnRegister;
    @FXML private Button btnCancel;

    @FXML private void handleTextField(ActionEvent event) {
        TextField source = (TextField) event.getSource();
    }

    @FXML private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnCancel) {
            loadWindow(btnCancel, "Inicio_client.fxml");
        }
        else if (event.getSource() == btnRegister && verifyTextFields()) {
            //FileCSV = new FileCSV("users.csv");

        }
        else {

        }
    }

    private boolean verifyTextFields(){
        // Verifica se todos os campos foram preenchidos
        if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty()
                || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtId.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            showMessage("Aviso", "Campos Incorretos", "Você deve preencher antes de continuar", Alert.AlertType.WARNING);
            return false;
        }
        // Verifica se o número de telefone é válido
        else if (isValidNumber(txtPhone)) {
            showMessage("Aviso", "Telefone Inválido", "O campo Telefone deve conter apenas numeros", Alert.AlertType.WARNING);
            return false;
        }
        /*else if (idExists()){
            return false;
        }*/
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
