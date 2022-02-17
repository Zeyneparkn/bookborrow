package com.zeynep.bookborrow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String firstName;

    private LocalDate memberSince;

    private LocalDate memberTill;

    private String gender;

}