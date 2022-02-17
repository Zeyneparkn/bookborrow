package com.zeynep.bookborrow.repository.custom;

import com.zeynep.bookborrow.entity.UserEntity;

import java.util.List;

public interface CustomUserRepository {
    List<UserEntity> createAllFromCsvFile(String fileName);
}
