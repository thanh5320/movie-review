package com.hust.movie_review.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "movies")
@Entity
@ToString(exclude ={"category", "country", "actors", "comments", "reviews"})
@EqualsAndHashCode(exclude = {"category", "country", "actors", "comments", "reviews"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updateAt;

    @Column(name = "type")
    private String type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonManagedReference
    Set<Actor> actors;

    @OneToMany(mappedBy="user")
    @JsonBackReference
    private Set<Comment> comments;

    @OneToMany(mappedBy="user")
    @JsonBackReference
    private Set<Review> reviews;
}
