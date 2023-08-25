package com.lcwr.db.api.mysql.entities;

/**
 * Represents a Book entity that maps to the "BOOK_TB" table in the MySql database bc2, I'm
 * connecting this entity with MySql database..
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TB")
public class Book {
    @Id
    private int id;
    private String name;
}
