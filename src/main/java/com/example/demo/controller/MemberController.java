package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Get all members (no group filter)
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    // Add a member to an existing group
    @PostMapping("/group/{groupId}")
    public ResponseEntity<Member> addMemberToGroup(@PathVariable Long groupId, @RequestBody Member member) {
        Member addedMember = memberService.addMemberToGroup(groupId, member);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    // Get all members of a specific group
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Member>> getMembersByGroup(@PathVariable Long groupId) {
        List<Member> members = memberService.getMembersByGroup(groupId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
