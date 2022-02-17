package com.zeynep.bookborrow.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String firstName;

    private LocalDate memberSince;

    private LocalDate memberTill;

    private String gender;
}