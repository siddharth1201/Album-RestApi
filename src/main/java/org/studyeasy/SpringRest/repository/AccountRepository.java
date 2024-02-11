package org.studyeasy.SpringRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringRest.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

    Optional<Account> findByEmail(String username);
    
}
