package chatserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger2 {

    private String path;
    public Logger2(String path) {
        this.path = path;
    }

    public void writeLogEntry(String line){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
