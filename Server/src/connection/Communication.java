package connection;

import csv.UserCsv;
import gui.Controller;
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
            receiver.read();

            // Se o objeto de leitura recebeu um usu�rio
            if (receiver.hasUser()) {
                System.out.println("recebeu usu�rio");
                String[] fields = receiver.getUserFields();
                // cria objeto com os dados do usuario
                User user = new User(fields);
                // Verifica se o usu�rio j� existe
                for(int i = 0; i < Controller.users.size(); i++) {
                    if (Controller.users.get(i).getId().compareTo(user.getId()) == 0){
                        // Se j� existe retorna erro
                        send.sendString("ja existe");
                        System.out.println("enviou erro");
                        return;
                    }
                }

                // Salva em arquivo
                if (userCsv.store(fields)){
                    // Envia reposta
                    Controller.users.add(user);
                    send.sendString("user saved");
                    System.out.println("enviou confirma��o");
                }
                else {
                    send.sendString("user not saved");
                    System.out.println("enviou erro");
                }
            }
            // Se o objeto de leitura recebeu um pedido de login
            else if (receiver.loginRequest()) {
                System.out.println("recebeu pedido de login");
                String[] fields = receiver.getUserFields();
                // Verifica se o usu�rio e a senha est�o corretos
                for(int i = 0; i < Controller.users.size(); i++) {
                    if (Controller.users.get(i).getId().compareTo(fields[0]) == 0 &&
                            Controller.users.get(i).getPassword().compareTo(fields[1]) == 0){
                        // Se existe retorna ok
                        send.sendString("ok");
                        System.out.println("logou");
                        return;
                    }
                }

                // Se n�o encontrou login e senha retorna fail
                send.sendString("fail");
                System.out.println("n�o logou");
            }
        }
    }
}
