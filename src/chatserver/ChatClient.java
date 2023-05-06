package chatserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatClient implements  Runnable {

    private Socket client;
    private ArrayList<ChatClient> chatClients;
    private PrintWriter printWriter;
    private BufferedReader reader;
    private String name;
    private Logger logger;
    private HashMap<String, ChatClient> chatClientMap;

    public ChatClient(Socket client, ArrayList<ChatClient> chatClients,
                      Logger logger, HashMap<String, ChatClient> chatClientMap) {
        this.client = client;
        this.chatClients = chatClients;
        this.logger = logger;
        this.name = "not-set";
        this.chatClientMap = chatClientMap;

        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            chatClients.add(this);
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessage(String message){
        logger.writeLogEntry("send: " + name + " >> " + message);
        printWriter.println(message);
        printWriter.flush();
    }

    public void close(){
        chatClients.stream().forEach(cc ->
                cc.sendMessage(name + " hat den Chat verlassen"));

        try {
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        printWriter.close();
        chatClients.remove(this);
    }

    @Override
    public void run() {

        String inputLine;
        try {
            while ((inputLine = reader.readLine()) != null){
                logger.writeLogEntry("received: " + name + " >> " + inputLine);

                System.out.println(name + ">>" + inputLine);

                // Befehle interpretieren

                // Befehl: <name>:name
                if (inputLine.startsWith("<name>:")) {
                    String name = inputLine.split(":")[1];

                    if (chatClientMap.containsKey(name)){
                        sendMessage("Name existiert bereits, überlege dir ein Alias");
                    }
                    else {
                        this.name = name;
                        chatClientMap.put(name, this);

                        chatClients.stream().forEach(cc ->
                                cc.sendMessage("Neuer Client hat sich verbunden: " + name));

                        /*for(ChatClient cc : chatClients){
                            cc.sendMessage("Neuer Client hat sich verbunden: " + name));
                        } */
                    }

                } // Befehl: <msg>:nachricht
                else if (inputLine.startsWith("<msg>:")){
                    String msg = inputLine.split(":")[1];

                    for(ChatClient cc : chatClients){
                        if (cc != this)
                            cc.sendMessage(msg);
                    }
                }// Befehl: <msgto>:empfänger:nachricht
                else if (inputLine.startsWith("<msgto>:")){
                    String[] parts = inputLine.split(":");
                    String recipient = parts[1];
                    String msg = parts[2];

                    /*for(ChatClient cc : chatClients){
                        if (cc.name.equals(recipient)){
                            cc.sendMessage(msg);
                        }
                    }*/
                    if (chatClientMap.containsKey(recipient)){
                        chatClientMap.get(recipient).sendMessage(msg);
                    }
                }
                else if (inputLine.equalsIgnoreCase("<list>")){
                    for (ChatClient cc : chatClients){
                        sendMessage(cc.name);
                    }
                }
                // Befehl: <bye>
                else if (inputLine.equalsIgnoreCase("<bye>")){
                    close();
                    return;
                }
                else{
                    sendMessage("Falscher Befehl!!");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
}
