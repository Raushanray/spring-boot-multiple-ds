package com.lcwr.db.api.mysql.repository;

/**
 * Repository interface for managing Book entities.
 * Provides methods to perform CRUD operations and custom queries on book data.
 */

import com.lcwr.db.api.mysql.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // Additional custom query methods can be defined here
}
