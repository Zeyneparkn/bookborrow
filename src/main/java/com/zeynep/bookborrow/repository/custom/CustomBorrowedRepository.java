package com.zeynep.bookborrow.repository.custom;

import com.zeynep.bookborrow.entity.BorrowedEntity;

import java.util.List;

public interface CustomBorrowedRepository {
    List<BorrowedEntity> createAllFromCsvFile(String fileName);
}
