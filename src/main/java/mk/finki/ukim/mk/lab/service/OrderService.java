package mk.finki.ukim.mk.lab.service;

import java.util.ArrayList;

import mk.finki.ukim.mk.lab.model.Order;


public interface OrderService{
    // Order placeOrder(String balloonColor, String clientName, String address);

    void saveOrder(Order order);
    ArrayList<Order> getOrders();
}
