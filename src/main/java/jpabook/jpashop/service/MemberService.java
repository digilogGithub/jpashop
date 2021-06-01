package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// final 이 붙은 member 변수를 자동으로 생성자로 생성함
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
/*
//  최신 버젼의 스프링에서는 생성자가 한개인 경우 @Autowired 가 자동으로 주입된다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 */

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 간단하게 구현 -> 추후 변경 생각
    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
