package com.example.Academy.service;

import com.example.Academy.Entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    public List<Member> getAllMembers();
    Member getMemberById(Long id);
    Member saveMember(Member member);
    void deleteMember(Long id);
    Member updateMember(Long id, Member member);
}