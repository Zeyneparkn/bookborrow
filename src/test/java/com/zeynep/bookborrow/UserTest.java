package com.zeynep.bookborrow;

import com.zeynep.bookborrow.dto.UserDto;
import com.zeynep.bookborrow.entity.BorrowedEntity;
import com.zeynep.bookborrow.repository.BorrowedRepository;
import com.zeynep.bookborrow.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    ModelMapper modelMapper;



    @Test
    void return_users_who_borrowed_given_date() {

        List<UserDto> borrowers =userService.findUserBorrowedAtDate("2021-07-13");
        Assertions.assertNotNull(borrowers);
        Assertions.assertFalse(borrowers.isEmpty());

        UserDto selectedUser = borrowers.iterator().next();
        List<BorrowedEntity> borrowedList= borrowedRepository.findByBorrowerId(selectedUser.getId());

        BorrowedEntity borrowed  = borrowedList.stream()
                .filter(borrowedItem -> Objects.equals(borrowedItem.getBorrowedFrom().toString(), "2021-07-13"))
                .findFirst()
                .orElse(null);

        UserDto userDto =modelMapper.map( borrowed.getBorrower(), UserDto.class);

        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(selectedUser,userDto);



    }
}
