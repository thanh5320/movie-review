package com.hust.movie_review.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "display_name", nullable = false)
    private String displayName;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
