package ecom;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BasketDataLoader {

    public static ArrayList<BasketData> load(String path) throws DataFileException {

        ArrayList<BasketData> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line;
            while ((line = br.readLine()) != null) {

                BasketData bd = new Gson().fromJson(line, BasketData.class);
                //BasketData bd = new BasketData("Graz", "DVD", 12.4, "Credit Card");
                list.add(bd);
            }

            return list;
        } catch (FileNotFoundException e) {
            throw new DataFileException("Path: " + path, e);
        } catch (IOException e) {
            throw new DataFileException("Path: " + path, e);
        }
    }

    public static ArrayList<BasketData> load(String path, Comparator<BasketData> comparator) throws DataFileException {
        ArrayList<BasketData> list = load(path);
        Collections.sort(list, comparator);
        return list;
    }
}
