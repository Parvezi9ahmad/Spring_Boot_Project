package com.springboot.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 60)
    private String name;
}
