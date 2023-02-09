package com.otus.authservice.repository;

import com.otus.authservice.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobilePhone(String mobilePhone);

    @Modifying
    @Query(value = "DELETE" + " FROM User AS user" + " WHERE user.id IN :ids")
    Integer deleteUsersByIdIn(@Param("ids")
    List<Long> ids);

    @Query(value = "SELECT user.id" + " FROM User AS user" +
    " LEFT JOIN user.roles role" + " WHERE user.id IN :ids AND" +
    "     role.name NOT LIKE 'role_admin'")
    List<Long> findIdsNotAdminUsersByIds(@Param("ids")
    List<Long> ids);

    @Transactional
    @Modifying
    @Query(value = "update users set password = :password where id = :id", nativeQuery = true)
    Integer changePassword(@Param("id") Long id, @Param("password") String password);
}
