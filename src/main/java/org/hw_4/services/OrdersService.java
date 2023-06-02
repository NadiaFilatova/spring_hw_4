package org.hw_4.services;

import org.hw_4.entity.Orders;
import org.hw_4.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> findAll(){
        return  ordersRepository.findAll();
    }
    @Transactional
    public void save(Orders order){
        ordersRepository.save(order);
    }
}
