package com.example.demo.controller;

import com.example.demo.entity.Groups;
import com.example.demo.entity.Member;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Create a new group
    @PostMapping
    public ResponseEntity<Groups> createGroup(@RequestBody Groups group) {
        Groups createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Groups>> getAllGroups() {
        List<Groups> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }


    // Add members to a group
    @PostMapping("/{groupId}/members")
    public ResponseEntity<Member> addMemberToGroup(@PathVariable Long groupId, @RequestBody Member member) {
        Member addedMember = groupService.addMemberToGroup(groupId, member);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long id) {
        Groups group = groupService.getGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        try {
            groupService.deleteGroupById(id);
            return ResponseEntity.ok("Group deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

