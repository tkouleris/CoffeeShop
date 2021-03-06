package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.dto.ApiResponse;
import com.tkouleris.coffeeshop.model.Item;
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
    public ResponseEntity<Object> all(@RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        List<Tables> tables = tableService.findAll(pageNo, pageSize, sortBy);
        ApiResponse apiResponse = new ApiResponse(true,"tables",tables);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @GetMapping(path = "{table_id}", produces = "application/json")
    public ResponseEntity<Object> get_item(@PathVariable("table_id") long table_id){
        Tables table = tableService.getTable(table_id);
        ApiResponse apiResponse = new ApiResponse(true,"table",table);
        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createTable(@RequestBody Tables table) throws Exception {
        Tables savedTable = tableService.createTable(table);
        ApiResponse apiResponse = new ApiResponse(true,"table saved",savedTable);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> updateTable(@Valid @RequestBody Tables table) throws Exception {
        Tables updatedTable = tableService.updateTable(table);
        ApiResponse apiResponse = new ApiResponse(true,"table saved",updatedTable);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{table_id}", produces = "application/json")
    public ResponseEntity<Object> deleteTable(@PathVariable("table_id") long table_id) throws Exception {
        tableService.deleteTable(table_id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
