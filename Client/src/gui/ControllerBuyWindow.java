package gui;

import connection.Communication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML private Button btnNotify;
    @FXML private TextField txtNameAvailable;
    @FXML private TextField txtNameUnavailable;

    // Tabela de Produtos disponíveis
    @FXML private TableView<Product> availableTable;
    @FXML private TableColumn<Product, String> availableNameColumn;
    @FXML private TableColumn<Product, String> availableVendorColumn;
    @FXML private TableColumn<Product, String> availableExpirationColumn;
    @FXML private TableColumn<Product, Float> availablePriceColumn;
    @FXML private TableColumn<Product, Integer> availableQuantityColumn;

    // Tabela de Produtos indisponíveis
    @FXML private TableView<Product> unavailableTable;
    @FXML private TableColumn<Product, String> unavailableNameColumn;
    @FXML private TableColumn<Product, String> unavailableVendorColumn;
    @FXML private TableColumn<Product, Float> unavailablePriceColumn;

    private ObservableList<Product> availableData = FXCollections.observableArrayList();
    private ObservableList<Product> unavailableData = FXCollections.observableArrayList();

    @FXML private void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Aqui");
        // Inicializa todas as observebleLists que contem o s dados dos produtos
        // Lista de usuários
        Communication communication = new Communication();
        ArrayList<Product> products = communication.getInventory();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getQuantity() == 0)
                unavailableData.add(product);
            else
                availableData.add(product);
        }

        // Inicializa as colunas da tabela de produtos disponíveis
        availableNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        availableVendorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vendor"));
        availableExpirationColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("expirationDay"));
        availablePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        availableQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));

        // Inicializa as colunas da tabela de produtos indisponíveis
        unavailableNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        unavailableVendorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vendor"));
        unavailablePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));

        availableTable.setItems(availableData);
        unavailableTable.setItems(unavailableData);
    }
}
