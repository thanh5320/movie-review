package com.hust.movie_review.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "actors")
@Entity
@ToString(exclude = {"country", "movies"})
@EqualsAndHashCode(exclude = {"country", "movies"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

    @Column(name = "year_birthday")
    private int yearBirthday;

    @Column(name = "gender")
    private String gender;

    @ManyToMany(mappedBy = "actors")
    Set<Movie> movies;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updateAt;
}
