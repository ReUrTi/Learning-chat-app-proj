package com.example.chat_app.repository;

import com.example.chat_app.model.DTO.InterlocutorDTO;
import com.example.chat_app.model.User;
import com.example.chat_app.model.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT new com.example.chat_app.model.DTO.UserDTO(u.id, u.nickname) FROM User u WHERE LOWER(u.nickname) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<UserDTO> findByNicknameLikeIgnoreCase(@Param("prefix") String prefix);

    @Query("SELECT new com.example.chat_app.model.DTO.UserDTO(u.id, u.nickname) FROM User u WHERE u.username = :username")
    UserDTO getUserDTOByUsername(@Param("username") String username);

    @Query("select u.nickname from User u where u.id = :id")
    Optional<String> getUserNicknameById(@Param("id") Long id);

    @Query("select new com.example.chat_app.model.DTO.InterlocutorDTO(u.nickname, a.id, b.id, c.id) from User u " +
            "left join BlockedUser a on a.blockerId = :interlocutorId and a.blockedId = :authUserId " +
            "left join BlockedUser b on b.blockerId = :authUserId and b.blockedId = :interlocutorId " +
            "left join PrivateChat c on (c.user1Id = :authUserId and c.user2Id = :interlocutorId) or (c.user1Id = :interlocutorId and c.user2Id = :authUserId) " +
            "where u.id = :interlocutorId")
    InterlocutorDTO getChatInfoById(@Param("authUserId") Long authUserId, @Param("interlocutorId") Long interlocutorId);
}