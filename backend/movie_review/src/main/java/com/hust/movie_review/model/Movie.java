package com.hust.movie_review.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;
    @Column(name = "director")
    private String director;
    @Column(name = "year")
    private int year;
    @Column(name = "rating")
    private double rating;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "trailer")
    private String trailer;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    Set<Actor> actors;

    @OneToMany(mappedBy="user")
    private Set<Comment> comments;
    @OneToMany(mappedBy="user")
    private Set<Review> reviews;
}
