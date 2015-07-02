package connection;

import csv.ProductCsv;
import csv.UserCsv;
import gui.ControllerRegisterWindow;
import gui.ControllerStartWindow;
import gui.ControllerUpdateWindow;
import market.Product;
import market.User;

import java.net.Socket;

/**
 * Created by Marcio on 28/06/2015.
 */
public class Communication implements Runnable{
    private Socket client;
    public Communication(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        Receiver receiver = new Receiver(client);
        Sender send = new Sender(client);
        UserCsv userCsv = new UserCsv("User.csv");

        while (true) {
            // Se leu corretamente
            if(receiver.read()){
                // Se o objeto de leitura recebeu um usuário
                if (receiver.hasUser()) {
                    boolean flag = true;
                    receiver.setUser(false);
                    System.out.println("recebeu usuário");
                    String[] fields = receiver.getUserFields();
                    // cria objeto com os dados do usuario
                    User user = new User(fields);
                    // Verifica se o usuário já existe
                    for(int i = 0; i < ControllerStartWindow.users.size(); i++) {
                        if (ControllerStartWindow.users.get(i).getId().compareTo(user.getId()) == 0){
                            // Se já existe retorna erro
                            send.sendString("ja existe");
                            System.out.println("enviou erro");
                            flag = false;
                        }
                    }

                    if (flag){
                        // Salva em arquivo
                        if (userCsv.store(fields)){
                            // Envia reposta
                            ControllerStartWindow.users.add(user);
                            send.sendString("user saved");
                            System.out.println("enviou confirmação");
                        }
                        else {
                            send.sendString("user not saved");
                            System.out.println("enviou erro");
                        }
                    }

                }
                // Se o objeto de leitura recebeu um pedido de login
                else if (receiver.loginRequest()) {
                    boolean flag = true;
                    receiver.setLoginRequest(false);
                    System.out.println("recebeu pedido de login");
                    String[] fields = receiver.getUserFields();
                    // Verifica se o usuário e a senha estão corretos
                    for(int i = 0; i < ControllerStartWindow.users.size(); i++) {
                        if (ControllerStartWindow.users.get(i).getId().compareTo(fields[0]) == 0 &&
                                ControllerStartWindow.users.get(i).getPassword().compareTo(fields[1]) == 0){
                            // Se existe retorna ok
                            send.sendString("ok");
                            System.out.println("logou");
                            flag = false;
                        }
                    }
                    if (flag) {
                        // Se não encontrou login e senha retorna fail
                        send.sendString("fail");
                        System.out.println("não logou");
                    }
                }
                // Se houve um pedido pela lista de produtos
                else if (receiver.sendList()) {
                    receiver.setSendList(false);
                    System.out.println("recebeu pedido de enviar estoque");

                    // Envia o tamanho da lista
                    send.sendString(Integer.toString(ControllerStartWindow.products.size()));
                    for (int i = 0; i < ControllerRegisterWindow.products.size(); i++) {
                        // Envia objeto produto
                        Product product = ControllerRegisterWindow.products.get(i);
                        send.sendString(product.getName());
                        send.sendString(Float.toString(product.getPrice()));
                        send.sendString(product.getVendor());
                        send.sendString(Integer.toString(product.getQuantity()));
                        send.sendString(product.expirationDay);

                    }
                }
                // Se houve uma solicitação de compra
                else if(receiver.hasBuy()) {
                    boolean flag = false;
                    receiver.setBuy(false);
                    // Verifica se existem produtos disponíveis
                    for (int i = 0; i < ControllerRegisterWindow.products.size(); i++) {
                        Product product = ControllerRegisterWindow.products.get(i);
                        // Se o produto foi encontrado e está disponível
                        if (product.getName().compareTo(receiver.getProductName()) == 0 && product.getQuantity() > 0){
                            // Diminui uma unidade
                            product.buyOne();
                            // Atualiza o arquivo de dados
                            new ProductCsv("Product.csv").updateFile(ControllerUpdateWindow.products);
                            // Envia resposta
                            send.sendString("ok");
                            System.out.println("comprou");
                            flag = true;
                        }
                    }
                    // Se o produto não esta mais disponível
                    if(!flag){
                        send.sendString("fail");
                        System.out.println("não comprou");
                    }
                }
            }
            else return;
        }
    }
}
