package org.swiggy.assignment.comparators;

import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;

import java.util.Comparator;

/**
 * @author sarwarkumar on 23/06/18
 * @project Swiggy
 */
public class DeliveryExecutiveComparator implements Comparator<DeliveryExecutive> {

    @Override
    public int compare(final DeliveryExecutive obj1, final DeliveryExecutive obj2) {
        return obj1.getLast_order_time().compareTo(obj2.getLast_order_time());
    }

}
