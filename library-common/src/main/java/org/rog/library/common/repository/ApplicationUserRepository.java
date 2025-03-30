package org.rog.library.common.repository;

import org.rog.library.common.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    @Query(value = "select user from ApplicationUser user where user.id =:id")
    Optional<ApplicationUser> findById(long id);
}
