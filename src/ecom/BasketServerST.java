package ecom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasketServerST {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(1234)) {

            System.out.println("Server started at port 1234");

            while (true) {
                System.out.println("waiting for client");
                Socket client = server.accept();
                System.out.println("client connected");

                EcommerceLogic ec = new EcommerceLogic(client);

                Thread thread = new Thread(ec); // Multithreading
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
