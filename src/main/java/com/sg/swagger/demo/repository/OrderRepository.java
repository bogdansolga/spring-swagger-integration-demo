package com.sg.swagger.demo.repository;

import com.sg.swagger.demo.model.Order;
import com.sg.swagger.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Order} entity
 *
 * @author bogdan.solga
 */
@Repository
@SuppressWarnings("unused")
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
