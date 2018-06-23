package org.swiggy.assignment.pojos;

import com.sun.tools.corba.se.idl.constExpr.Or;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

/**
 * @author akjoshi on 23/06/18
 * @project Swiggy
 */
public class Order{

    private String restaurant_location, ordered_time;
    private Integer id;
    private Location location;
    private LocalDateTime last_order_time;
    private boolean isComplete;

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LocalDateTime getLast_order_time() {
        return last_order_time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLast_order_time(LocalDateTime last_order_time) {
        this.last_order_time = last_order_time;
    }

//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public void setRestaurant_location(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public String getOrdered_time() {
        return ordered_time;
    }

    public void setOrdered_time(String ordered_time) {
        this.ordered_time = ordered_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLatLong(String location) {
        String[] coordinates = location.split(",");
        this.setLocation(new Location(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1])));
//        this.setLatitude(Double.parseDouble(coordinates[0]));
//        this.setLongitude(Double.parseDouble(coordinates[1]));
    }

    public void setLastOrderTime(String lastOrderTime) {
        this.setLast_order_time(LocalDateTime.parse(lastOrderTime, formatter));
    }


    @Override
    public String toString(){
        return getId() + ", "+getLast_order_time();
    }

}
