package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findFirstByItem(String itemCode);
}
