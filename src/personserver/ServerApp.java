package personserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {


        try(ServerSocket serverSocket = new ServerSocket(1234))
        {
            System.out.println("Server started, waiting on clients ...");

            int session = 10;
            while (session > 0) {
                session--;
                Socket client = serverSocket.accept();
                System.out.println("Client connected");
                ClientCommunication cc = new ClientCommunication(client);
                Thread th = new Thread(cc);
                th.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
