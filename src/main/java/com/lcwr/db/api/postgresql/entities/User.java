package com.lcwr.db.api.postgresql.entities;

/**
 * Represents a User entity that maps to the "users" table in the postgresql database bc2, I'm
 * connecting this entity with postgresql database..
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    private int id;
    private String userName;
}
