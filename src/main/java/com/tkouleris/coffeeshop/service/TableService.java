package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TableService {

    protected TableRepository tablesRepository;

    public TableService(TableRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public List<Tables> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Tables> pageResult = tablesRepository.findAll(paging);
        return pageResult.getContent();
    }

    public Tables getTable(long table_id) {
        return tablesRepository.findById(table_id).orElse(null);
    }

    public Tables createTable(Tables table) throws Exception {
        if (tableCodeExists(table.getTable_code())) {
            throw new Exception("Table " + table.getTable_code() + " already exists");
        }
        return tablesRepository.save(table);
    }

    public Tables updateTable(Tables table) throws Exception {
        if (table.getId() == 0) {
            throw new Exception("Table ID not set!");
        }

        Tables recordToUpdate = tablesRepository.findById(table.getId()).orElse(null);
        if (tableRecordNotExists(recordToUpdate)) {
            throw new Exception("Table does not exist");
        }

        if (tableCodeExists(table.getTable_code()) && tableCodeDoesNotBelongToTheUpdatedTable(table)) {
            throw new Exception("Talbe code " + table.getTable_code() + " already exists!");
        }

        if (table.getTable_code() != null) recordToUpdate.setTable_code(table.getTable_code());
        recordToUpdate.setActive(table.isActive());

        return tablesRepository.save(recordToUpdate);
    }

    public void deleteTable(long table_id) throws Exception {
        Tables tableToDelete = tablesRepository.findById(table_id).orElse(null);
        if (tableRecordNotExists(tableToDelete)) {
            throw new Exception("Table does not exist!");
        }

        tablesRepository.delete(tableToDelete);
    }

    private boolean tableRecordNotExists(Tables table) {
        return table == null;
    }

    private boolean tableCodeExists(String tableCode) {
        return tablesRepository.findByTableCode(tableCode).orElse(null) != null;
    }

    private boolean tableCodeDoesNotBelongToTheUpdatedTable(Tables table) {
        Tables updatedTable = tablesRepository.findByTableCode(table.getTable_code()).orElse(null);
        return Objects.requireNonNull(updatedTable).getId() != table.getId();
    }
}
