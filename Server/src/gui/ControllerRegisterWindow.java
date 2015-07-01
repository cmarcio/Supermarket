package gui;

import csv.ProductCsv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import market.Product;

/**
 * Created by Marcio on 01/07/2015.
 */
public class ControllerRegisterWindow extends ControllerStartWindow {
    @FXML private Button btnCancel;
    @FXML private Button btnRegister;
    @FXML private TextField txtName;
    @FXML private TextField txtVendor;
    @FXML private TextField txtPrice;

    @FXML void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        // Se o usu�rio clicou em cancelar
        if (btn == btnCancel) {
            loadWindow(btnCancel, "Principal_server.fxml");
        }
        // Se o usu�rio clicou em registrar
        else if (btn == btnRegister && !txtName.getText().isEmpty() && !txtPrice.getText().isEmpty() && !txtVendor.getText().isEmpty()) {
            // Verifica se o produto j� est� cadastrado
            for (int i = 0; i < ControllerRegisterWindow.products.size(); i++) {
                // O produto j� existe
                if (ControllerRegisterWindow.products.get(i).getName().compareTo(txtName.getText()) == 0){
                    showMessage("Erro", "Produto inv�lido", "Este produto j� foi cadastrado", Alert.AlertType.ERROR);
                    return;
                }
            }
            // Salva as vari�veis dos campos
            String[] str = {txtName.getText(), txtPrice.getText(), txtVendor.getText(), "0", "undefined"};
            // Salva o produto em arquivo
            ProductCsv productCsv = new ProductCsv("Product.csv");
            if(productCsv.store(str)){
                // Se foi salvo em arquivo corretamente
                // Cria objeto e salva na lista
                Product product = new Product(str);
                ControllerStartWindow.products.add(product);
                showMessage("Sucesso", txtName.getText(), "Cadastrado com sucesso", Alert.AlertType.INFORMATION);
            }
            else showMessage("Erro", null, "Falha ao salvar novo produto em arquivo", Alert.AlertType.ERROR);
        }
        // Se algum campo est� em branco
        else
            showMessage("Aviso", "Campos Inconrretos", "Preencha corretamente todos os campos", Alert.AlertType.WARNING);
    }
}
