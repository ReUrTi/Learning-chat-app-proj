package com.example.chat_app.repository;

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
}