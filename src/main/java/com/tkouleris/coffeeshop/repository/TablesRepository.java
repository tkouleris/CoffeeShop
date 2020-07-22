package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends CrudRepository<Tables, Long> {
}
