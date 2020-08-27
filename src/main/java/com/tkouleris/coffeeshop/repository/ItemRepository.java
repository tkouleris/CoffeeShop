package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Optional<Item> findFirstByItem(String itemCode);
}
