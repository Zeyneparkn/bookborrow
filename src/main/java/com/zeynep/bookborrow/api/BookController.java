package com.zeynep.bookborrow.api;

import com.zeynep.bookborrow.dto.BookDto;
import com.zeynep.bookborrow.dto.BorrowedBookWithUserDto;
import com.zeynep.bookborrow.service.impl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/getBorrowedBookByUserAndDateRange")
    ResponseEntity<List<BookDto>> getBorrowedBookByUserAndDateRange(@RequestBody BorrowedBookWithUserDto borrowedBookWithUserDto){
        return ResponseEntity.ok(bookService.findBookByUserAndGivenDateRange(borrowedBookWithUserDto));
    }
}
