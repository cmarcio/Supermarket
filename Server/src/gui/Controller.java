package gui;

import connection.ConnectionsManager;
import csv.ProductCsv;
import csv.UserCsv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import market.Product;
import market.User;

import java.util.ArrayList;

public class Controller {
    Thread server = null;
    public static ArrayList<User> users;
    public static ArrayList<Product> products;

    public Controller(){
        // Cria e inicializa a thread de controle das conexões
        server = new Thread(new ConnectionsManager());
        server.start();
        // Cria os arrays com os usuários cadastrados
        users = new UserCsv("User.csv").readAll();
        products = new ProductCsv("Product.csv").readAll();
        if(users == null) {
            users = new ArrayList<User>();
        }
        if (products == null)
            products = new ArrayList<Product>();
    }
}