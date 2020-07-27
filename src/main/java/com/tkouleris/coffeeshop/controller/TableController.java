package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TableRepository;
import com.tkouleris.coffeeshop.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tables")
public class TableController {

    protected TableService tableService;

    public TableController(TableService tableService)
    {
        this.tableService = tableService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createTable(@RequestBody Tables table) throws Exception {
        Tables savedTable = tableService.createTable(table);
        return new ResponseEntity<>(savedTable, HttpStatus.OK);
    }
}
