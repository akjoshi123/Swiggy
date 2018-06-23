package org.swiggy.assignment.criterias;

import org.swiggy.assignment.comparators.DeliveryExecutiveComparator;
import org.swiggy.assignment.comparators.OrdersComparator;
import org.swiggy.assignment.pojos.AssignedOrders;
import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;
import org.swiggy.assignment.utils.HaversineDistance;

import java.util.*;

/**
 * @author akjoshi on 23/06/18
 * @project Swiggy
 */
public class OrderPriorityCriteria implements Criteria {

    @Override
    public List<AssignedOrders> assignOrders(List<Order> orders, List<DeliveryExecutive> deliveryExecutives) {

        List<AssignedOrders> result = new ArrayList<AssignedOrders>();

        Collections.sort(orders, new OrdersComparator());
        Collections.sort(deliveryExecutives, new DeliveryExecutiveComparator());

        for (Iterator<Order> ordersIterator = orders.iterator(); ordersIterator.hasNext(); ) {
            Order order = ordersIterator.next();
            TreeMap<Double, List<DeliveryExecutive>> distances = new TreeMap<Double, List<DeliveryExecutive>>();

            for (Iterator<DeliveryExecutive> deliveryExecutivesIterator = deliveryExecutives.iterator(); deliveryExecutivesIterator.hasNext(); ) {
                DeliveryExecutive deliveryExecutive = deliveryExecutivesIterator.next();
//                if (deliveryExecutive.isAssigned())
//                    continue;

                Double haversineD = HaversineDistance.distance(order.getLocation().getLatitude(), order.getLocation().getLongitude(), deliveryExecutive.getLocation().getLatitude(), deliveryExecutive.getLocation().getLongitude());

                if (distances.containsKey(haversineD)) {
                    distances.get(haversineD).add(deliveryExecutive);
                } else {
                    List<DeliveryExecutive> deliveryExecutives1 = new ArrayList<DeliveryExecutive>();
                    deliveryExecutives1.add(deliveryExecutive);

                    distances.put(haversineD, deliveryExecutives1);
                }
            }


            if(distances.size() == 0) {
                result.add(new AssignedOrders(order.getId(), -1));
            } else {
                Map.Entry<Double, List<DeliveryExecutive>> s = distances.firstEntry();
                DeliveryExecutive deliveryExecutive = s.getValue().get(0);

                result.add(new AssignedOrders(order.getId(), deliveryExecutive.getId()));
                deliveryExecutive.getLocation().setLatitude(order.getLocation().getLatitude());
                deliveryExecutive.getLocation().setLongitude(order.getLocation().getLongitude());

             //   ordersIterator.remove();
             //   deliveryExecutive.setAssigned(true);
            }

        }

        return result;
    }

}
