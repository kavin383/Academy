package com.example.Academy.service;

import com.example.Academy.Entity.Member;
import com.example.Academy.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class memberServiceImpl implements MemberService{

    @Autowired
    private final MemberRepository memberRepository;

//    public memberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member>member= memberRepository.findById(id);
        return member.orElseThrow(()->new RuntimeException("member not found"));
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);

    }

    @Override
    public Member updateMember(Long id, Member member) {
        if (memberRepository.existsById(id)) {
            member.setId(id);
            return memberRepository.save(member);
        }
        return null;
    }
}
