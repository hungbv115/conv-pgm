package com.process.old.controller;

import com.process.old.service.ProgramAService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProgramAController {

    private final ProgramAService programAService;

    public ProgramAController(ProgramAService programAService) {
        this.programAService = programAService;
    }

    @GetMapping("/pgmA")
    public ModelAndView showProgramA() {
        List<String> lines = programAService.executeProgramA();
        ModelAndView mav = new ModelAndView("pgmA");
        mav.addObject("programLines", lines);
        mav.addObject("programName", "PROGRAM A");
        return mav;
    }

    @PostMapping("/pgmA/exit")
    public ModelAndView exitProgramA(RedirectAttributes redirectAttributes) {
        // Khi thoát về màn hình cmd, có thể đẩy thông báo
        redirectAttributes.addFlashAttribute("resultMessage", "Returned from Program A.");
        return new ModelAndView("redirect:/cmd");
    }
}



