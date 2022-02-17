package com.zeynep.bookborrow.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class CsvUtils {
    private final static CSVParser parser = new CSVParserBuilder()
            .withSeparator(',')
            .withQuoteChar('"')
            .build();

    public static List<String[]> readFromCSV(String filePath){

        try {
            return new CSVReaderBuilder(new FileReader(filePath))
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build().readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

    public static Optional<LocalDate> findAsDate(String[] data, int index) {
        if (data == null || data.length < index) {
            return Optional.empty();
        }
        return Optional.ofNullable(data[index])
                .filter(date -> !date.isEmpty())
                .map(date -> LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy")));

    }
}
