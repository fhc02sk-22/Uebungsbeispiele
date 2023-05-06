package ecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasketAnalyzer {

    private ArrayList<BasketData> baskets;

    public BasketAnalyzer(ArrayList<BasketData> baskets) {
        this.baskets = baskets;
    }

    public List<BasketData> getEveryNthBasket(int n) {
        List<BasketData> list = new ArrayList<>();

        for(int i = 0;i < baskets.size(); i+=n) {
            list.add(baskets.get(i));
        }

/*        int count = 0;
        for (BasketData bd : baskets) {
            if (count % n == 0) {
                list.add(bd);
            }
            count++;
        }*/

        return list;
    }

    public List<BasketData> filterBaskets(String paymentType, Double from, Double to) {
        List<BasketData> list = new ArrayList<>();

        for (BasketData bd : baskets) {
            if (bd.getPaymentType().equals(paymentType) // equals!!
                    && bd.getOrderTotal() >= from && bd.getOrderTotal() <= to)
                list.add(bd);
        }

        return list;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory() {
        HashMap<String, ArrayList<Double>> map = new HashMap<>();

        for(BasketData bd : baskets) {
            if (!map.containsKey(bd.getProductCategory()))
                map.put(bd.getProductCategory(), new ArrayList<Double>());

            ArrayList<Double> list = map.get(bd.getProductCategory()); // Referenzparameter
            list.add(bd.getOrderTotal());

            //map.put(bd.getProductCategory(), list);
        }

        return map;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory2() {
        HashMap<String, ArrayList<Double>> map = new HashMap<>();

        for(BasketData bd : baskets) {
            if (!map.containsKey(bd.getProductCategory())){
                ArrayList<Double> list =  new ArrayList<Double>();
                list.add(bd.getOrderTotal());
                map.put(bd.getProductCategory(), list);
            }
            else {
                ArrayList<Double> list = map.get(bd.getProductCategory()); // Referenzparameter
                list.add(bd.getOrderTotal());
                //map.put(bd.getProductCategory(), list);
            }
        }
        return map;
    }

}
