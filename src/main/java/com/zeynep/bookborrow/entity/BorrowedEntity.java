package com.zeynep.bookborrow.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Borrowed")
@Data
public class BorrowedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrower")
    private UserEntity borrower;

    @ManyToOne
    @JoinColumn(name = "book")
    private BookEntity book;

    private LocalDate borrowedFrom;

    private LocalDate borrowedTo;
}