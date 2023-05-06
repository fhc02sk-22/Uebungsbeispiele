package ecom;

import java.util.Comparator;

public class BasketComparator implements Comparator<BasketData> {
    @Override
    public int compare(BasketData o1, BasketData o2) {

        int comp = o1.getBuyingLocation().compareTo(o2.getBuyingLocation());
        if (comp == 0) {
            comp = o2.getOrderTotal().compareTo(o1.getOrderTotal());
        }

        return comp;
    }
}
