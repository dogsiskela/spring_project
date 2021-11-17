package mk.finki.ukim.mk.lab.repository.impl;

import java.util.*;

import org.springframework.stereotype.Repository;
import mk.finki.ukim.mk.lab.model.Order;

@Repository
public class InMemoryOrderRepository {

    ArrayList<Order> orders = new ArrayList<Order>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

}
