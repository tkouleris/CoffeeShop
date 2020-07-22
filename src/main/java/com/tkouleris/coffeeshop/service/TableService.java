package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TablesRepository;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    protected TablesRepository tablesRepository;

    public TableService(TablesRepository tablesRepository)
    {
        this.tablesRepository = tablesRepository;
    }

    public Tables createTable(Tables table)
    {
        return tablesRepository.save(table);
    }
}
