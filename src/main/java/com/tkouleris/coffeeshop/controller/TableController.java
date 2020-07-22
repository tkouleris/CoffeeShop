package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TablesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tables")
public class TableController {

    protected TablesRepository tablesRepository;

    public TableController(TablesRepository tablesRepository)
    {
        this.tablesRepository = tablesRepository;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createTable(@RequestBody Tables table)
    {
        Tables savedTable = tablesRepository.save(table);
        return new ResponseEntity<>(savedTable, HttpStatus.OK);
    }
}
