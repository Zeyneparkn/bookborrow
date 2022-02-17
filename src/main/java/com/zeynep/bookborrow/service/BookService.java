package com.zeynep.bookborrow.service;

import com.zeynep.bookborrow.dto.BookDto;
import com.zeynep.bookborrow.dto.BorrowedBookWithUserDto;

import java.util.List;

public interface BookService {
    List<BookDto> findBookByUserAndGivenDateRange(BorrowedBookWithUserDto borrowedBookWithUserDto);
}
