package org.swiggy.assignment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.swiggy.assignment.criterias.OrderPriorityCriteria;
import org.swiggy.assignment.criterias.Criteria;
import org.swiggy.assignment.factory.CriteriaFactory;
import org.swiggy.assignment.pojos.AssignedOrders;
import org.swiggy.assignment.pojos.DeliveryExecutive;
import org.swiggy.assignment.pojos.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author akjoshi on 23/06/18
 * @project Swiggy
 */
public class Driver {

    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper();
        Driver driver = new Driver();
        try {
            List<DeliveryExecutive> deliveryExecutives = mapper.readValue(new FileInputStream("/Users/sarwarkumar/Documents/JavaProject/Swiggy/input/deliveryExecutive.json"), new TypeReference<List<DeliveryExecutive>>(){});
            List<Order> orders = mapper.readValue(new FileInputStream("/Users/sarwarkumar/Documents/JavaProject/Swiggy/input/orders.json"),  new TypeReference<List<Order>>(){} );

            for(Order order: orders) {
                order.setLatLong(order.getRestaurant_location());
                order.setLastOrderTime(order.getOrdered_time());

            }

            for(DeliveryExecutive deliveryExecutive: deliveryExecutives) {
                deliveryExecutive.setLatLong(deliveryExecutive.getCurrent_location());
                deliveryExecutive.setLastOrderTime(deliveryExecutive.getLast_order_delivered_time());
            }

            driver.getAssignment(orders, deliveryExecutives, "default");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getAssignment(List<Order> orders, List<DeliveryExecutive> deliveryExecutives, String criteria) {
        Criteria assignmentCriteria = CriteriaFactory.getCriteria(criteria);

        List<AssignedOrders> results = assignmentCriteria.assignOrders(orders, deliveryExecutives);

        for(AssignedOrders result: results) {
            if(result.getDeliveryExecutiveId() == -1) {
                System.out.println("Order Id : " + result.getOrderId() + " no Delivery Executive available");
            } else {
                System.out.println("Order Id : " + result.getOrderId() + " is assigned to Delivery Executive Id : " + result.getDeliveryExecutiveId());
            }
        }
    }
}
