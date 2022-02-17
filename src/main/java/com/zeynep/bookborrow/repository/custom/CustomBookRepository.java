package com.zeynep.bookborrow.repository.custom;

import com.zeynep.bookborrow.entity.BookEntity;

import java.util.List;

public interface CustomBookRepository {
    List<BookEntity> createAllFromCsvFile(String fileName);
}
