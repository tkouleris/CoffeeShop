package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/tables")
public class TableController {

    protected TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Object> all(){
        List<Tables> tables = tableService.findAll();
        return new ResponseEntity<>(tables,HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createTable(@RequestBody Tables table) throws Exception {
        Tables savedTable = tableService.createTable(table);
        return new ResponseEntity<>(savedTable, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> updateTable(@Valid @RequestBody Tables table) throws Exception {
        Tables updatedTable = tableService.updateTable(table);
        return new ResponseEntity<>(updatedTable, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{table_id}", produces = "application/json")
    public ResponseEntity<Object> deleteTable(@PathVariable("table_id") long table_id) throws Exception {
        tableService.deleteTable(table_id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
