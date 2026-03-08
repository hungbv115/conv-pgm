package com.process.old.service.impl;

import com.process.old.service.ProgramBService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramBServiceImpl implements ProgramBService {

    @Override
    public List<String> buildInitialLines() {
        List<String> lines = new ArrayList<>();
        lines.add("PROGRAM B - CUSTOMER INPUT SCREEN");
        lines.add("---------------------------------");
        lines.add("Enter customer information below and press F9 to process.");
        return lines;
    }

    @Override
    public List<String> process(String customerId, String customerName, String note) {
        List<String> lines = new ArrayList<>();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        lines.add("PROGRAM B EXECUTION  " + now);
        lines.add("  CUSTOMER ID   : " + (customerId == null ? "" : customerId.trim()));
        lines.add("  CUSTOMER NAME : " + (customerName == null ? "" : customerName.trim()));
        lines.add("  NOTE          : " + (note == null ? "" : note.trim()));
        lines.add("  STATUS        : Data accepted (demo).");

        return lines;
    }
}


