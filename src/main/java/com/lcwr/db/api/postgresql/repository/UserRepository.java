package com.lcwr.db.api.postgresql.repository;

/**
 * Repository interface for managing User entities.
 * Provides methods to perform CRUD operations and custom queries on user data.
 */

import com.lcwr.db.api.postgresql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Additional custom query methods can be defined here
}
