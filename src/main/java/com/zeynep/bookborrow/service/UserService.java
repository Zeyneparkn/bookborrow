package com.zeynep.bookborrow.service;
import com.zeynep.bookborrow.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUserAtLeastOneBook();
    List<UserDto> findUserCurrentlyBorrowAnything();
    List<UserDto> findUserBorrowedAtDate(String date);
}
