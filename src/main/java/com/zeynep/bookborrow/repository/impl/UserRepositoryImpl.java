package com.zeynep.bookborrow.repository.impl;

import com.zeynep.bookborrow.entity.UserEntity;
import com.zeynep.bookborrow.repository.UserRepository;
import com.zeynep.bookborrow.repository.custom.CustomUserRepository;
import com.zeynep.bookborrow.utils.CsvUtils;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    private final UserRepository userRepository;

    public UserRepositoryImpl(@NonNull @Lazy UserRepository  userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> createAllFromCsvFile(String fileName) {
        List<UserEntity> userEntities = CsvUtils.readFromCSV(fileName).stream().skip(1)
                .map(line -> {
                    try {
                        UserEntity userEntity = new UserEntity();
                        userEntity.setName(line[0]);
                        userEntity.setFirstName(line[1]);
                        CsvUtils.findAsDate(line, 2).ifPresent(userEntity::setMemberSince);
                        CsvUtils.findAsDate(line, 3).ifPresent(userEntity::setMemberTill);
                        userEntity.setGender(line[4]);
                        return userEntity;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return userRepository.saveAll(userEntities);
    }
}
