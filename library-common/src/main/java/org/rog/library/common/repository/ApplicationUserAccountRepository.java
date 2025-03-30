package org.rog.library.common.repository;

import org.rog.library.common.entity.ApplicationUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserAccountRepository extends JpaRepository<ApplicationUserAccount, String> {
    @Query(value = "select user from ApplicationUserAccount user where user.login =:login")
    Optional<ApplicationUserAccount> findByLogin(String login);
}
