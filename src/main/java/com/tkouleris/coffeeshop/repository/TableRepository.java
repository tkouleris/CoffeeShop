package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends CrudRepository<Tables, Long> {

    Optional<Tables> findByTableCode(String tableCode);
}
