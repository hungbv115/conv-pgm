package com.process.old.controller;

import com.process.old.service.CommandResult;
import com.process.old.service.CommandService;
import com.process.old.service.ScreenLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CommandController {

    private final CommandService commandService;
    private final ScreenLogService screenLogService;

    public CommandController(CommandService commandService, ScreenLogService screenLogService) {
        this.commandService = commandService;
        this.screenLogService = screenLogService;
    }

    @PostMapping("/cmd")
    public ModelAndView executeCmd(@RequestParam("command") String command,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        List<String> logLines = screenLogService.getOrCreateLog(session, ScreenLogService.SCREEN_CMD_MAIN);

        CommandResult result = commandService.execute(command, logLines);

        if (result.getResultMessage() != null) {
            redirectAttributes.addFlashAttribute("resultMessage", result.getResultMessage());
        }
        if (result.getLastCommand() != null) {
            redirectAttributes.addFlashAttribute("lastCommand", result.getLastCommand());
        }

        return new ModelAndView("redirect:" + result.getRedirectPath());
    }
}

