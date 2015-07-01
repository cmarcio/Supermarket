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
public class ControllerUpdateWindow extends ControllerStartWindow {
    @FXML private Button btnUpdate;
    @FXML private Button btnCancel;
    @FXML private TextField txtName;
    @FXML private TextField txtExpiration;
    @FXML private TextField txtQuantity;

    @FXML void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        // Se o usuário cancelar
        if (btn == btnCancel)
            loadWindow(btnCancel, "Principal_server.fxml");
        // Se o usuário quiser atualizar e preencheu corretamente todos os campos
        else if (btn == btnUpdate && !txtName.getText().isEmpty() && !txtExpiration.getText().isEmpty() && !txtQuantity.getText().isEmpty()){
            // verifica se o produto existe
            for (int i = 0; i < ControllerRegisterWindow.products.size(); i++) {
                Product product = ControllerRegisterWindow.products.get(i);
                if(product.getName().compareTo(txtName.getText()) == 0) {
                    // Se existe atualiza na memória e em disco
                    product.setExpirationDay(txtExpiration.getText());
                    product.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    ProductCsv productCsv = new ProductCsv("Product.csv");
                    productCsv.updateFile(ControllerUpdateWindow.products);
                    showMessage("Sucesso", txtName.getText(), "Atualizado com sucesso", Alert.AlertType.INFORMATION);
                    return;
                }
            }
            showMessage("Erro", txtName.getText(), "Nao foi cadastrado ainda", Alert.AlertType.ERROR);
        }
        // Se algum campo está incorreto
        else
            showMessage("Aviso", "Campos Inconrretos", "Preencha corretamente todos os campos", Alert.AlertType.WARNING);

    }
}
