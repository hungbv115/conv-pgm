package com.process.old.service.impl;

import com.process.old.service.CommandResult;
import com.process.old.service.CommandService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    @Override
    public CommandResult execute(String command, List<String> logLines) {
        String trimmed = command == null ? "" : command.trim();

        if (trimmed.isEmpty()) {
            return new CommandResult("/cmd", "No command entered.", null);
        }

        logLines.add("===> " + trimmed);

        // Lệnh A: chuyển sang màn Program A riêng
        if ("A".equalsIgnoreCase(trimmed)) {
            return new CommandResult("/pgmA", null, trimmed);
        }
        // Lệnh B: chuyển sang màn Program B riêng
        if ("B".equalsIgnoreCase(trimmed)) {
            return new CommandResult("/pgmB", null, trimmed);
        }
        // Lệnh C: chuyển sang màn Program C riêng
        if ("C".equalsIgnoreCase(trimmed)) {
            return new CommandResult("/pgmC", null, trimmed);
        }

        String upper = trimmed.toUpperCase();
        String result;

        if ("HELP".equals(upper)) {
            logLines.add("     AVAILABLE COMMANDS:");
            logLines.add("       HELP               - Show this help");
            logLines.add("       CLEAR              - Clear screen");
            logLines.add("       TIME               - Show current time");
            logLines.add("       DATE               - Show current date");
            logLines.add("       ECHO <text>        - Echo text back");
            logLines.add("       A                  - Run PROGRAM A screen");
            logLines.add("       B                  - Run PROGRAM B screen");
            logLines.add("       C                  - Run PROGRAM C screen");
            result = "Help displayed.";
        } else if ("CLEAR".equals(upper)) {
            logLines.clear();
            result = "Screen cleared.";
        } else if ("TIME".equals(upper)) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            logLines.add("     CURRENT TIME: " + time);
            result = "Time shown.";
        } else if ("DATE".equals(upper)) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            logLines.add("     CURRENT DATE: " + date);
            result = "Date shown.";
        } else if (upper.startsWith("ECHO ")) {
            String text = command.substring(5);
            logLines.add("     " + text);
            result = "Echoed text.";
        } else {
            result = "Executed: \"" + trimmed + "\" at " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            logLines.add("     " + result);
        }

        return new CommandResult("/cmd", result, trimmed);
    }
}


