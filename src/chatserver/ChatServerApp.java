package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatServerApp {

    public static void main(String[] args) {
        Logger logger = new Logger("D:\\chatserverlog.log");

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("server started ...");
            ArrayList<ChatClient> chatClients = new ArrayList<>();
            HashMap<String, ChatClient> chatClientMap = new HashMap<>();

            while (true) {
                System.out.println("waiting on client ...");
                Socket client = serverSocket.accept();
                System.out.println("client connected ...");
                ChatClient cc = new ChatClient(client, chatClients, logger, chatClientMap);

                Thread th = new Thread(cc);
                th.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            logger.close();
        }

    }
}
