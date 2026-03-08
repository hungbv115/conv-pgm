package com.process.old.controller;

import com.process.old.service.ScreenLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class CmdMainController {

    private final ScreenLogService screenLogService;

    public CmdMainController(ScreenLogService screenLogService) {
        this.screenLogService = screenLogService;
    }

    @GetMapping("/cmd")
    public ModelAndView showCmd(HttpSession session) {
        List<String> logLines = screenLogService.getOrCreateLog(session, ScreenLogService.SCREEN_CMD_MAIN);

        ModelAndView mav = new ModelAndView("cmdMain");
        mav.addObject("logLines", logLines);

        // Thông tin hệ thống giả lập
        mav.addObject("systemName", "AS400 DEMO SYSTEM");
        mav.addObject("userName", "DEMOUSER");
        mav.addObject("serverTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return mav;
    }
}

