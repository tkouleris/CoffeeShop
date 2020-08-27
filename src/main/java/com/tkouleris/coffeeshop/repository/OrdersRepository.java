package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    @Query("select o from Orders o left join Tables t where t.tableCode = ?1")
    Optional<Orders> findByTableCode(String tableCode);
}
