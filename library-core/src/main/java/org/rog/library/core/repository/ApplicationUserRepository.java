package org.rog.library.core.repository;

import org.rog.library.core.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    @Query(value = "select user from ApplicationUser user where user.login =:login")
    Optional<ApplicationUser> findByLogin(String login);
}
