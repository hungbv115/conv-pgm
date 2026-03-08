package com.process.old.service.impl;

import com.process.old.service.ProgramAService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramAServiceImpl implements ProgramAService {

    @Override
    public List<String> executeProgramA() {
        List<String> lines = new ArrayList<>();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        lines.add("PGM A STARTED  " + now);
        lines.add("  - Initializing resources...");
        lines.add("  - Performing demo business logic...");
        lines.add("  - Program A completed successfully.");

        return lines;
    }
}


