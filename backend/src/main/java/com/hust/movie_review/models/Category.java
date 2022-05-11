package com.hust.movie_review.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy="country")
    private Set<Movie> movies;
}
