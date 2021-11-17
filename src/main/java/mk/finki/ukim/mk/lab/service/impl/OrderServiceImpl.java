package mk.finki.ukim.mk.lab.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryOrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final InMemoryOrderRepository orderRepository;

    public OrderServiceImpl(InMemoryOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public ArrayList<Order> getOrders() {
        return orderRepository.getOrders();
    }

}
