package org.swiggy.assignment.criterias;

import org.swiggy.assignment.comparators.DeliveryExecutiveComparator;
import org.swiggy.assignment.pojos.AssignedOrders;
import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;
import org.swiggy.assignment.utils.HaversineDistance;

import java.util.*;

/**
 * @author akjoshi on 23/06/18
 * @project Swiggy
 */
public class DeliveryExecutiveCriteria implements Criteria {

    @Override
    public List<AssignedOrders> assignOrders(List<Order> orders, List<DeliveryExecutive> deliveryExecutives) {

        List<AssignedOrders> result = new ArrayList<AssignedOrders>();

        Collections.sort(deliveryExecutives, new DeliveryExecutiveComparator());

        for (Iterator<DeliveryExecutive> deliveryExecutivesIterator = deliveryExecutives.iterator(); deliveryExecutivesIterator.hasNext(); ) {
            DeliveryExecutive deliveryExecutive = deliveryExecutivesIterator.next();
            TreeMap<Double, List<Order>> distances = new TreeMap<Double, List<Order>>();

            for (Iterator<Order> ordersIterator = orders.iterator(); ordersIterator.hasNext(); ) {
                Order order = ordersIterator.next();
            //    if (order.isComplete())
            //        continue;

                Double haversineD = HaversineDistance.distance(order.getLocation().getLatitude(), order.getLocation().getLongitude(), deliveryExecutive.getLocation().getLatitude(), deliveryExecutive.getLocation().getLongitude());

                if (distances.containsKey(haversineD)) {
                    distances.get(haversineD).add(order);
                } else {
                    List<Order> order1 = new ArrayList<Order>();
                    order1.add(order);

                    distances.put(haversineD, order1);
                }
            }


            if(distances.size() == 0) {
                result.add(new AssignedOrders(deliveryExecutive.getId(), -1));
            } else {
                Map.Entry<Double, List<Order>> s = distances.firstEntry();
                Order order1 = s.getValue().get(0);

                result.add(new AssignedOrders(deliveryExecutive.getId(), order1.getId()));
                deliveryExecutive.getLocation().setLatitude(deliveryExecutive.getLocation().getLatitude());
                deliveryExecutive.getLocation().setLongitude(deliveryExecutive.getLocation().getLongitude());

            //    deliveryExecutivesIterator.remove();
            //    order1.setComplete(true);
            }

        }

        return result;
    }

}
