package org.swiggy.assignment.factory;

import org.swiggy.assignment.criterias.Criteria;
import org.swiggy.assignment.criterias.DeliveryExecutiveCriteria;
import org.swiggy.assignment.criterias.OrderPriorityCriteria;

/**
 * @author sarwarkumar on 23/06/18
 * @project Swiggy
 */
public class CriteriaFactory {

    public static Criteria getCriteria(String criteriaName) {
         if(criteriaName.equals("deliveryexecutives"))
            return new DeliveryExecutiveCriteria();


        return new OrderPriorityCriteria();
    }
}
