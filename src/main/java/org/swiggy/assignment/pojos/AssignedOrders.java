package org.swiggy.assignment.pojos;

/**
 * @author akjoshi on 23/06/18
 * @project Swiggy
 */
public class AssignedOrders {

    private int orderId, deliveryExecutiveId;

    public AssignedOrders(int orderId, int deliveryExecutiveId) {
        this.orderId = orderId;
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryExecutiveId() {
        return deliveryExecutiveId;
    }

    public void setDeliveryExecutiveId(int deliveryExecutiveId) {
        this.deliveryExecutiveId = deliveryExecutiveId;
    }
}
