package com.group.libraryapp.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    boolean existsByName(String name);

    long countByAge(Integer age);

}
