package org.swiggy.assignment.criteria;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.swiggy.assignment.criterias.Criteria;
import org.swiggy.assignment.criterias.OrderPriorityCriteria;
import org.swiggy.assignment.pojos.AssignedOrders;
import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author sarwarkumar on 23/06/18
 * @project Swiggy
 */
public class AssignmentCriteriaTest {

    private  List<DeliveryExecutive> deliveryExecutives;
    private  List<Order> orders;
    @Before
    public void initialize() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            deliveryExecutives = mapper.readValue(new FileInputStream("/Users/sarwarkumar/Documents/JavaProject/Swiggy/input/deliveryExecutive.json"), new TypeReference<List<DeliveryExecutive>>(){});
            orders = mapper.readValue(new FileInputStream("/Users/sarwarkumar/Documents/JavaProject/Swiggy/input/orders.json"),  new TypeReference<List<Order>>(){} );

            for(Order order: orders) {
                order.setLatLong(order.getRestaurant_location());
                order.setLastOrderTime(order.getOrdered_time());

            }

            for(DeliveryExecutive deliveryExecutive: deliveryExecutives) {
                deliveryExecutive.setLatLong(deliveryExecutive.getCurrent_location());
                deliveryExecutive.setLastOrderTime(deliveryExecutive.getLast_order_delivered_time());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testOrderIdExecutionCriteria() {
        Criteria assignmentCriteria = new OrderPriorityCriteria();
        List<AssignedOrders> results = assignmentCriteria.assignOrders(orders, deliveryExecutives);

        assertEquals(results.get(0).getOrderId(), 3);
        assertEquals(results.get(1).getOrderId(), 1);
        assertEquals(results.get(2).getOrderId(), 2);
    }

    @Test
    public void testOrderExecutiveMapping() {
        Criteria assignmentCriteria = new OrderPriorityCriteria();
        List<AssignedOrders> results = assignmentCriteria.assignOrders(orders, deliveryExecutives);

        assertEquals(results.get(0).getDeliveryExecutiveId(), 1);
        assertEquals(results.get(1).getDeliveryExecutiveId(), 2);
        assertEquals(results.get(2).getDeliveryExecutiveId(), 2);
    }
}
