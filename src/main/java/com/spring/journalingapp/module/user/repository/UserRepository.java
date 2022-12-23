package com.spring.journalingapp.module.user.repository;

import com.spring.journalingapp.module.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByIdAndTokenId(String userId, String tokenId);

    Optional<User> findByEmail(String email);
}
