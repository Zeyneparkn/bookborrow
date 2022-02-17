package com.zeynep.bookborrow.repository.impl;

import com.zeynep.bookborrow.entity.BookEntity;
import com.zeynep.bookborrow.repository.BookRepository;
import com.zeynep.bookborrow.repository.custom.CustomBookRepository;
import com.zeynep.bookborrow.utils.CsvUtils;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements CustomBookRepository {

    private final BookRepository bookRepository;

    public BookRepositoryImpl(@NonNull @Lazy BookRepository  bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> createAllFromCsvFile(String fileName) {
        List<BookEntity> bookEntities= CsvUtils.readFromCSV(fileName).stream().skip(1)
                .map(line -> {
                    try {
                        BookEntity bookEntity = new BookEntity();
                        bookEntity.setTitle(line[0]);
                        bookEntity.setAuthor(line[1]);
                        bookEntity.setGenre(line[2]);
                        bookEntity.setPublisher(line[3]);
                        return bookEntity;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return bookRepository.saveAll(bookEntities);
    }
}
