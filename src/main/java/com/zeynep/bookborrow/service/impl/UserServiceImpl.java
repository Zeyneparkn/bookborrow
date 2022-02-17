package com.zeynep.bookborrow.service.impl;

import com.zeynep.bookborrow.dto.UserDto;
import com.zeynep.bookborrow.entity.UserEntity;
import com.zeynep.bookborrow.repository.BorrowedRepository;
import com.zeynep.bookborrow.repository.UserRepository;
import com.zeynep.bookborrow.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BorrowedRepository borrowedRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BorrowedRepository borrowedRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.borrowedRepository = borrowedRepository;
    }

    @Override
    public List<UserDto> findUserAtLeastOneBook() {
        List<UserEntity> userEntities =borrowedRepository.findUserAtLeastOneBook();

        //    List<UserDto> userDtos= userEntities
        //            .stream()
        //             .map(user -> modelMapper.map(user, UserDto.class))
        //            .collect(Collectors.toList());

        return mapList(userEntities, UserDto.class);
    }

    @Override
    public List<UserDto> findUserCurrentlyBorrowAnything() {
        List<UserEntity> userEntities=  userRepository.findUserCurrentlyBorrowAnything();
        return mapList(userEntities, UserDto.class);
    }

    @Override
    public List<UserDto> findUserBorrowedAtDate(String date) {
        if( !date.isEmpty()){
            LocalDate localDate=  LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<UserEntity> userEntities= userRepository.findUserBorrowedAtDate(localDate);

            return mapList(userEntities, UserDto.class);
        }
        return null;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
