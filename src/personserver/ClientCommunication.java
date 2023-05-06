package personserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientCommunication implements Runnable {

    private Socket client;

    public ClientCommunication(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("run gestartet");
        PersonLoader pl = new PersonLoader("D:\\Temp\\personen1.txt");

        try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
             ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream()))
        {
            System.out.println("Streams erzeugt");
            ArrayList<Person> persons= pl.load();

            String input;
            while ((input = br.readLine()) != null) {

                System.out.println("Client sendete >> " + input);

                if (input.equalsIgnoreCase("exit")){
                    return;
                } else if (input.equalsIgnoreCase("getall")){

                    for (Person p : persons){
                        oos.writeObject(p);
                    }
                    oos.writeObject(null);
                    oos.flush();

                } else if (input.startsWith("get ")) {
                    //  "get 7"
                    String[] part = input.split(" ");// [0] = "get" || [1] = "7"
                    int id = Integer.parseInt(part[1]);

                    for(Person p : persons)
                    {
                        if (p.getId() == id){
                            oos.writeObject(p);
                            oos.flush();
                            break;
                        }
                    }
                }
                else {
                    System.out.println("ungültiger befehl: " + input);
                }

            }

        } catch (PersonLoadException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ende von run");
    }
}
