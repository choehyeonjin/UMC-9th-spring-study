package com.example.umc9th.domain.member.repository;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 3) 마이페이지 조회 쿼리
    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);
}
