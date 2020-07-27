package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TableRepository;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    protected TableRepository tablesRepository;

    public TableService(TableRepository tablesRepository)
    {
        this.tablesRepository = tablesRepository;
    }

    public Tables createTable(Tables table) throws Exception {
        Tables t = tablesRepository.findByTableCode(table.getTable_code()).orElse(null);
        if(t != null)
        {
            throw new Exception("Table " + table.getTable_code() + " already exists");
        }
        return tablesRepository.save(table);
    }
}
