package com.dev.mpolacek.ebayclone.repositories;

import com.dev.mpolacek.ebayclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);

    User findByUsername(String username);


}

