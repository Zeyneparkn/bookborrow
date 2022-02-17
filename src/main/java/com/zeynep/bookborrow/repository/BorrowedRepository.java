package com.zeynep.bookborrow.repository;

import com.zeynep.bookborrow.entity.BorrowedEntity;
import com.zeynep.bookborrow.entity.UserEntity;
import com.zeynep.bookborrow.repository.custom.CustomBorrowedRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedRepository extends JpaRepository<BorrowedEntity, Long>, CustomBorrowedRepository {

    @Query("select distinct b.borrower from BorrowedEntity b")
    List<UserEntity> findUserAtLeastOneBook();

    List<BorrowedEntity> findByBorrowerId(Long id);

}
