package com.ashish.threadboost.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "userName")
    String userName;

    @Column(name="classEnrolled")
    Integer classEnrolled;

    BigDecimal marks;

    public User(String userName, Integer classEnrolled, BigDecimal marks) {
        this.userName = userName;
        this.classEnrolled = classEnrolled;
        this.marks = marks;
    }
}
