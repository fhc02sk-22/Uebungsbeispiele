package ecom;

import java.util.ArrayList;
import java.util.Scanner;

public class DemoApp {

    public static void main(String[] args) {

        try {
            System.out.println("start reading");
            ArrayList<BasketData> list = BasketDataLoader.load(".\\data\\buyings.json", new BasketComparator());

            System.out.println("list.size() = " + list.size());
            System.out.println("list.get(0) = " + list.get(0));
            
            
            BasketAnalyzer ba = new BasketAnalyzer(list);

            System.out.println("ba.getEveryNthBasket(100).size() = " + ba.getEveryNthBasket(100).size());
            System.out.println("ba.filterBaskets(\"MasterCard\", 100.0, 200.0) = " + ba.filterBaskets("MasterCard", 100.0, 120.0).size());
            System.out.println("ba.groupByProductCategory() = " + ba.groupByProductCategory().keySet().size());
        } catch (DataFileException e) {
            e.printStackTrace();
        }


    }
}
