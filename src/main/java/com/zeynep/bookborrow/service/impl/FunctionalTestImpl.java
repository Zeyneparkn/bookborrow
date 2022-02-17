package com.zeynep.bookborrow.service.impl;

import com.zeynep.bookborrow.repository.BookRepository;
import com.zeynep.bookborrow.repository.BorrowedRepository;
import com.zeynep.bookborrow.repository.UserRepository;
import com.zeynep.bookborrow.service.FunctionalTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class FunctionalTestImpl implements FunctionalTest {

    private final UserRepository userRepository;
    private final BorrowedRepository borrowedRepository;
    private final BookRepository bookRepository;


    @PostConstruct
    public void setup() {
        prepareData();
    }


    @Override
    public void prepareData() {
        borrowedRepository.deleteAll();
        userRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.createAllFromCsvFile("data/user.csv");
        bookRepository.createAllFromCsvFile("data/books.csv");
        borrowedRepository.createAllFromCsvFile("data/borrowed.csv");
    }
}
