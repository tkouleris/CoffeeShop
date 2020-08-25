package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
}
