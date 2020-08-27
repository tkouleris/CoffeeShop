package com.tkouleris.coffeeshop.repository;

import com.tkouleris.coffeeshop.model.Tables;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends PagingAndSortingRepository<Tables, Long> {

    Optional<Tables> findByTableCode(String tableCode);

}
