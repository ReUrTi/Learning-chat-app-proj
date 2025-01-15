package com.example.chat_app.repository;

import com.example.chat_app.model.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlockRepository extends JpaRepository<BlockedUser, Long> {

    @Query("SELECT COUNT(bu) FROM BlockedUser bu WHERE bu.blockerId = :blockerId")
    int countAllById(@Param("blockerId") Long blockerId);

    @Query("SELECT CASE WHEN COUNT(bu) > 0 THEN TRUE ELSE FALSE END FROM BlockedUser bu WHERE bu.blockerId = :blockerId AND bu.blockedId = :blockedId")
    boolean existsByParams(@Param("blockerId") Long blockerId, @Param("blockedId") Long blockedId);

    @Modifying
    @Query("DELETE FROM BlockedUser bu WHERE bu.blockerId = :blockerId AND bu.blockedId = :blockedId")
    void deleteByBlockerIdAndBlockedId(@Param("blockerId") Long blockerId, @Param("blockedId") Long blockedId);
}
