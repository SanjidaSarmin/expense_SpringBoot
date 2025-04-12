package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Groups;
import com.example.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    // Get all groups
    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    // Get group by ID
    public Groups getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
    }

    // Create a new group
    public Groups createGroup(Groups group) {
        return groupRepository.save(group);
    }

    // Update an existing group
    public Groups updateGroup(Long id, Groups updatedGroup) {
        Groups group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));

        group.setName(updatedGroup.getName());
        // Set other fields as needed

        return groupRepository.save(group);
    }

    // Delete a group by ID
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
