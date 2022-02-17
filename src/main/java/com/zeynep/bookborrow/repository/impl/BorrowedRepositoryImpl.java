package com.zeynep.bookborrow.repository.impl;

import com.zeynep.bookborrow.entity.BorrowedEntity;
import com.zeynep.bookborrow.repository.BookRepository;
import com.zeynep.bookborrow.repository.BorrowedRepository;
import com.zeynep.bookborrow.repository.UserRepository;
import com.zeynep.bookborrow.repository.custom.CustomBorrowedRepository;
import com.zeynep.bookborrow.utils.CsvUtils;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BorrowedRepositoryImpl implements CustomBorrowedRepository {

    private final BorrowedRepository borrowedRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowedRepositoryImpl(@NonNull @Lazy BorrowedRepository borrowedRepository, @NonNull @Lazy UserRepository userRepository, @NonNull @Lazy BookRepository bookRepository) {
        this.borrowedRepository = borrowedRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override

    public List<BorrowedEntity> createAllFromCsvFile(String fileName){
        List<BorrowedEntity> borrowedEntities= CsvUtils.readFromCSV(fileName).stream().skip(1)
                .map(line -> {
                    try {
                        BorrowedEntity borrowedEntity = new BorrowedEntity();
                        userRepository.findByFullName(line[0].replaceAll("\"", "")).stream().findFirst().ifPresent(borrowedEntity::setBorrower);
                        bookRepository.findByTitle(line[1]).stream().findFirst().ifPresent(borrowedEntity::setBook);
                        CsvUtils.findAsDate(line, 2).ifPresent(borrowedEntity::setBorrowedTo);
                        CsvUtils.findAsDate(line, 3).ifPresent(borrowedEntity::setBorrowedFrom);
                        return borrowedEntity;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return borrowedRepository.saveAll(borrowedEntities);

    }
}
