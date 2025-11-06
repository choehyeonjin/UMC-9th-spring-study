package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 1) 리뷰 작성 쿼리: 리뷰 작성 전에 성공 미션 존재 여부 확인
    @Query("""
        select count(mm) > 0
        from MemberMission mm
        where mm.member.id = :memberId
            and mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.SUCCESS
            and mm.mission.store.id = :storeId
    """)
    boolean existsSuccessAtStore(Long memberId, Long storeId);

    // 2) 내가 진행중/완료한 미션 목록 조회 (페이징 포함)
    @Query("""
        select mm
        from MemberMission mm
        where mm.member.id = :memberId
            and mm.status in :statuses
        order by case mm.status
            when com.example.umc9th.domain.mission.enums.MissionStatus.IN_PROGRESS then 0
            else 1
            end, mm.updatedAt desc
    """)
    Page<MemberMission> findMyMissions(Long memberId, Collection<MissionStatus> statuses, Pageable pageable);

    // 4) 특정 지역에서 완료한 미션 개수
    @Query("""
        select count(mm)
        from MemberMission mm
        where mm.member.id = :memberId
            and mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.SUCCESS
            and mm.mission.store.region.id = :regionId
    """)
    Long countSuccessInRegion(Long memberId, Long regionId);
}