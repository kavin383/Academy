package com.example.Academy.controller;


import com.example.Academy.Entity.Member;
import com.example.Academy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers()
    {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id){
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);

    }
    @PostMapping
    public Member createMember(@RequestBody Member member){
        return memberService.saveMember(member);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id,@RequestBody Member member){
        Member members = memberService.updateMember(id,member);
        if(members !=null) {
            return ResponseEntity.ok(members);
        }else {
            return ResponseEntity.notFound().build();
        }


    }

//    public ResponseEntity<Member> updateMember(@PathVariable Long id) {
//        member.setId(id);
//        Member members = memberService.saveMembers(member);
//        return ReponseEntity.save(members);
//    }



}
