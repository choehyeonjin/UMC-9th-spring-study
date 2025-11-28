package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 4) 특정 지역에서 도전 가능한 미션 조회
    @Query("""
        select m
        from Mission m
        where m.store.region.id = :regionId
            and not exists (
                select mm.id from MemberMission mm
                where mm.mission.id = m.id
                and mm.member.id = :memberId
                and mm.status in (
                com.example.umc9th.domain.mission.enums.MissionStatus.IN_PROGRESS,
                com.example.umc9th.domain.mission.enums.MissionStatus.SUCCESS
                )
            )
        order by m.deadline asc, m.id desc
    """)
    Page<Mission> findAvailableMissions(Long regionId, Long memberId, Pageable pageable);

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
