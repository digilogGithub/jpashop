package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
//@Rollback(false)
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long savedId = memberService.join(member);

        //then
        // Assertions.assertThat().isEqualTo();
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
//    @Rollback(false)
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
//        memberService.join(member2);
//        try {
//            memberService.join(member2); // 예외가 발생해야 한다
//        } catch (IllegalStateException e) {
//            return;
//        }
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        //then
//        Assertions.assertThat().isEqualTo();
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        fail("예외가 발생해야 한다");
    }
}
