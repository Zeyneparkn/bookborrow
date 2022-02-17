package com.zeynep.bookborrow.service.impl;

import com.zeynep.bookborrow.dto.BookDto;
import com.zeynep.bookborrow.dto.BorrowedBookWithUserDto;
import com.zeynep.bookborrow.entity.BookEntity;
import com.zeynep.bookborrow.repository.BookRepository;
import com.zeynep.bookborrow.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private ModelMapper modelMapper;
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookDto> findBookByUserAndGivenDateRange(BorrowedBookWithUserDto borrowedBookWithUserDto) {
        List<BookEntity> bookEntities= bookRepository.findByUserWithDateRange(borrowedBookWithUserDto.getUserId(),
                LocalDate.parse(borrowedBookWithUserDto.getStartedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) ,
                LocalDate.parse( borrowedBookWithUserDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return  bookEntities.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }
}
