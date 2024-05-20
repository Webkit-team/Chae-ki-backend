package com.chaekibackend.users.domain.interfaces;

import com.chaekibackend.users.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByNo(Long no);
    Boolean existsByUsername(String username);
}
