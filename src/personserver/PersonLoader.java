package personserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonLoader {
    private String path;

    public PersonLoader(String path) {
        this.path = path;
    }

    public ArrayList<Person> load() throws PersonLoadException {

        ArrayList<Person> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line;
            while ((line = br.readLine()) != null){
                String[] columns = line.split(";");
                if (columns.length != 3)
                    throw new PersonLoadException("Fehler beim Einlesen von Zeile: " + line);

                Person p = new Person(Integer.parseInt(columns[0]), columns[1], columns[2]);
                results.add(p);
            }

        } catch (FileNotFoundException e) {
            throw new PersonLoadException(e);
        } catch (IOException e) {
            throw new PersonLoadException(e);
        }
        return results;
    }
}
