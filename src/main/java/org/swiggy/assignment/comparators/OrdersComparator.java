package org.swiggy.assignment.comparators;

import org.swiggy.assignment.pojos.Order;

import java.util.Comparator;

/**
 * @author sarwarkumar on 23/06/18
 * @project Swiggy
 */
public class OrdersComparator implements Comparator<Order> {

    @Override
    public int compare(final Order obj1, final Order obj2) {
        return obj1.getLast_order_time().compareTo(obj2.getLast_order_time());
    }

}
