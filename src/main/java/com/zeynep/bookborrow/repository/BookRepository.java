package com.zeynep.bookborrow.repository;

import com.zeynep.bookborrow.entity.BookEntity;
import com.zeynep.bookborrow.repository.custom.CustomBookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> , CustomBookRepository {

    List<BookEntity> findByTitle(String title);

    @Query("select book from BookEntity  book, BorrowedEntity borrowed , UserEntity user " +
            "where user.id = :userId and user.id= borrowed.borrower.id and borrowed.book.id= book.id " +
            "and borrowed.borrowedTo < :endDate and borrowed.borrowedFrom> :startDate")
    List<BookEntity> findByUserWithDateRange(Long  userId, LocalDate startDate, LocalDate endDate);


}
