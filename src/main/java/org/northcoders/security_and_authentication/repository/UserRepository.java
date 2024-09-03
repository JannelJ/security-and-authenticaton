package org.northcoders.security_and_authentication.repository;

import org.northcoders.security_and_authentication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public Optional<User> findByGithubUsername(String githubUsername);
}
