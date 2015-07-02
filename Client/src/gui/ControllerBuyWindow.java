package gui;

import connection.Communication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import market.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 01/07/2015.
 */
public class ControllerBuyWindow extends ControllerStartWindow implements Initializable{
    @FXML private Button btnBuy;
    @FXML private TextField txtNameAvailable;

    // Tabela de Produtos dispon�veis
    @FXML private TableView<Product> availableTable;
    @FXML private TableColumn<Product, String> availableNameColumn;
    @FXML private TableColumn<Product, String> availableVendorColumn;
    @FXML private TableColumn<Product, String> availableExpirationColumn;
    @FXML private TableColumn<Product, Float> availablePriceColumn;
    @FXML private TableColumn<Product, Integer> availableQuantityColumn;

    // Tabela de Produtos indispon�veis
    @FXML private TableView<Product> unavailableTable;
    @FXML private TableColumn<Product, String> unavailableNameColumn;
    @FXML private TableColumn<Product, String> unavailableVendorColumn;
    @FXML private TableColumn<Product, Float> unavailablePriceColumn;

    private ObservableList<Product> availableData = FXCollections.observableArrayList();
    private ObservableList<Product> unavailableData = FXCollections.observableArrayList();

    @FXML private void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        boolean flag = false;
        // Se o usu�rios solicitar uma compra
        if (btn == btnBuy && !txtNameAvailable.getText().isEmpty()) {
            // Verifica se o item est� dispon�vel no estoque
            for(int i = 0; i < availableData.size(); i++) {
                if (availableData.get(i).getName().compareTo(txtNameAvailable.getText()) ==0 )
                    flag = true;
            }
            // Se o item n�o est� na lista de itens dispon�veis sai do m�todo
            if (!flag) {
                showMessage("Erro", "Item inexistente", "O item solicitado n�o est� na nossa lista de produtos dispon�veis", Alert.AlertType.ERROR);
                return;
            }

            // Pede para o servidor efetivar a compra
            Communication communication = new Communication();
            if (communication.buy(txtNameAvailable.getText())){
                // Se o produto ainda est� em estoque
                showMessage("Parabens", "Compra Efetuada", "Em email ser� enviado com as instrucoes de pagamento", Alert.AlertType.INFORMATION);
                // Chama a janela de novo
                loadWindow(btnBuy, "Principal_cliente.fxml");
            }
            // Se o produto n�o est� mais dispon�vel no servidor
            else
                showMessage("Erro", null, "Produto esgotado", Alert.AlertType.ERROR);


        }
        else{
            showMessage("Aviso", "Compo Vazio", "Preencha o campo com o nome do produto desejado", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Aqui");
        // Inicializa todas as observebleLists que contem o s dados dos produtos
        // Lista de usu�rios
        Communication communication = new Communication();
        ArrayList<Product> products = communication.getInventory();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getQuantity() == 0)
                unavailableData.add(product);
            else
                availableData.add(product);
        }

        // Inicializa as colunas da tabela de produtos dispon�veis
        availableNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        availableVendorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vendor"));
        availableExpirationColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("expirationDay"));
        availablePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        availableQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));

        // Inicializa as colunas da tabela de produtos indispon�veis
        unavailableNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        unavailableVendorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vendor"));
        unavailablePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));

        availableTable.setItems(availableData);
        unavailableTable.setItems(unavailableData);
    }
}
