package threading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileSplitter {

    private ArrayList<String> evenLines = new ArrayList<>();
    private ArrayList<String> oddLines = new ArrayList<>();

    public void load(String path){

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line;
            int lineCounter = 0;
            while ((line = br.readLine()) != null){
                lineCounter++;
                if (lineCounter % 2 == 0)
                    evenLines.add(line);
                else
                    oddLines.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getEvenLines() {
        return evenLines;
    }

    public ArrayList<String> getOddLines() {
        return oddLines;
    }
}
