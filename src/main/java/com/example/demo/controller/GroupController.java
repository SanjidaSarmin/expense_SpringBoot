package com.example.demo.controller;

import com.example.demo.entity.Groups;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Get all groups
    @GetMapping
    public ResponseEntity<List<Groups>> getAllGroups() {
        List<Groups> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Get group by ID
    @GetMapping("/{id}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long id) {
        Groups group = groupService.getGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Create a new group
    @PostMapping
    public ResponseEntity<Groups> createGroup(@RequestBody Groups group) {
        Groups createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    // Update an existing group
    @PutMapping("/{id}")
    public ResponseEntity<Groups> updateGroup(@PathVariable Long id, @RequestBody Groups updatedGroup) {
        Groups group = groupService.updateGroup(id, updatedGroup);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Delete a group by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

