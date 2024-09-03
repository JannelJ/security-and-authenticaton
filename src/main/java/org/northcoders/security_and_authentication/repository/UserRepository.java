package org.northcoders.security_and_authentication.repository;

import jakarta.persistence.Table;
import org.northcoders.security_and_authentication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Table(name = "Users")
public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByGithubUsername(String githubUsername);
}
