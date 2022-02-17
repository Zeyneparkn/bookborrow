package com.zeynep.bookborrow.repository;

import com.zeynep.bookborrow.entity.UserEntity;
import com.zeynep.bookborrow.repository.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
    @Query(nativeQuery = true, value = "select distinct u.* from user u left join borrowed b on u.id = b.borrower " +
            "where b.id is null")
    List<UserEntity> findUserCurrentlyBorrowAnything();

    @Query("select distinct  u from UserEntity u,BorrowedEntity b WHERE u.id = b.borrower.id and b.borrowedFrom =:date")
    List<UserEntity> findUserBorrowedAtDate(LocalDate date);

    @Query("select u from UserEntity u where concat(u.name,',',u.firstName) = :name")
    List<UserEntity> findByFullName(String name);
}
