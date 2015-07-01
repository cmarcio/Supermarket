package gui;

import connection.ConnectionsManager;
import csv.ProductCsv;
import csv.UserCsv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import market.Product;
import market.User;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerStartWindow {
    private static Thread server = null;
    public static ArrayList<User> users = null;
    public static ArrayList<Product> products = null;

    @FXML private Button btnRefresh;
    @FXML private Button btnRegister;
    @FXML private Button btnList;

    public ControllerStartWindow(){
        if (server == null) {
            // Cria e inicializa a thread de controle das conexões
            server = new Thread(new ConnectionsManager());
            server.start();
        }
        if (users == null)
            // Cria os arrays com os usuários cadastrados
            users = new UserCsv("User.csv").readAll();
        if (products == null)
            products = new ProductCsv("Product.csv").readAll();
        if(users == null)
            users = new ArrayList<User>();
        if (products == null)
            products = new ArrayList<Product>();
    }

    @FXML void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        // Se o usuário clicou em cadastrar produto
        if (btn == btnRegister) {
            loadWindow(btnRegister, "CadastrarProduto_server.fxml");
        }
        // Se o usuário clicou em listar produto
        else if(btn == btnList) {
            loadWindow(btnList, "Listar_server.fxml");
        }
        // Se o usuário clicou em atualizar estoque
        else{
            loadWindow(btnRefresh, "AtualizarEstoque_server.fxml");
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