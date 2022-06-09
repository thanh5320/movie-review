package com.hust.movie_review.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "roles")
@Entity
@ToString(exclude ={"users"})
@EqualsAndHashCode(exclude = {"users"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updateAt;
}
