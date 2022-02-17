package com.zeynep.bookborrow.dto;

import lombok.Data;

@Data
public class BorrowedBookWithUserDto {

    private Long userId;
    private String startedDate;
    private String endDate;

}
