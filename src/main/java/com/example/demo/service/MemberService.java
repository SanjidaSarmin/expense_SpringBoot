package com.example.demo.service;


import com.example.demo.entity.Groups;
import com.example.demo.entity.Member;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Get all members (regardless of group)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    // Add a new member to an existing group
    public Member addMemberToGroup(Long groupId, Member member) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));

        member.setGroup(group);  // Associate the member with the group
        return memberRepository.save(member);  // Save the member
    }

    // Get all members of a specific group
    public List<Member> getMembersByGroup(Long groupId) {
        return memberRepository.findByGroupId(groupId);  // Query for members by group ID
    }

    // Get a member by ID
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }
}
