package org.swiggy.assignment.criterias;

import org.swiggy.assignment.pojos.AssignedOrders;
import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;

import java.util.List;

/**
 * @author sarwarkumar on 23/06/18
 * @project Swiggy
 */
public interface Criteria {

    List<AssignedOrders> assignOrders(List<Order> orders, List<DeliveryExecutive> deliveryExecutives);
}
