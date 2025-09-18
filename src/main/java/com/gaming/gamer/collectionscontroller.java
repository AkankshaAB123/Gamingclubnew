package com.gaming.gamer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collections")
public class collectionscontroller {

    @Autowired
    private collectionsservice service;

    @PostMapping
    public collections create(@RequestBody collections collection) {
        return service.create(collection);
    }

    @GetMapping
    public List<collections> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public collections getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public collections update(@PathVariable String id, @RequestBody collections collection) {
        return service.update(id, collection);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "Collection deleted successfully";
    }
}
