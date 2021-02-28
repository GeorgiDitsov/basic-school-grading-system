package com.ditsov.basicschoolgradingsystem.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    	Optional<User> findByUsername(final String username);

}
