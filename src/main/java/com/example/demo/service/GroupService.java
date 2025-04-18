package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Groups;
import com.example.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Create a new group
    public Groups createGroup(Groups group) {
        return groupRepository.save(group);
    }

    // Add a new member to an existing group
    public Member addMemberToGroup(Long groupId, Member member) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        member.setGroup(group);  // Linking member to group
        return memberRepository.save(member);
    }

    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    // Get group by ID
    public Groups getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroupById(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new RuntimeException("Group not found with id: " + id);
        }
    }
}
