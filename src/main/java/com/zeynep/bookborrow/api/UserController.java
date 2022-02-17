package com.zeynep.bookborrow.api;

import com.zeynep.bookborrow.dto.UserDto;
import com.zeynep.bookborrow.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/getAtLeastOneBook")
    public ResponseEntity<List<UserDto>> getUsersAtLeastOneBook(){
        List<UserDto> response=  userService.findUserAtLeastOneBook();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCurrentlyBorrowAnything")
    public ResponseEntity<List<UserDto>> getUsersCurrentlyBorrowAnything(){
        return ResponseEntity.ok(userService.findUserCurrentlyBorrowAnything());
    }

    @GetMapping("/borrowedGivenDate/{date}")
    public ResponseEntity<List<UserDto>> borrowedGivenDate(@PathVariable("date") String date){
        return ResponseEntity.ok(userService.findUserBorrowedAtDate(date));
    }
}
