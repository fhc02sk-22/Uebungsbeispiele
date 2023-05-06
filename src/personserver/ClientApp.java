package personserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 1234);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()))
        {
            System.out.println("get 3");
            bw.write("get 3");
            bw.newLine();
            bw.flush();
            System.out.println(ois.readObject());

            System.out.println("getall");
            bw.write("getall");
            bw.newLine();
            bw.flush();

            Person p = null;
            while ((p = (Person) ois.readObject()) != null)
                System.out.println(p);


            System.out.println("get 1");
            bw.write("get 1");
            bw.newLine();
            bw.flush();
            System.out.println(ois.readObject());

            bw.write("exit");
            bw.newLine();
            bw.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
