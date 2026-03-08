package com.process.old.controller;

import com.process.old.service.LineColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LineColumnController {

    private final LineColumnService lineColumnService;

    public LineColumnController(LineColumnService lineColumnService) {
        this.lineColumnService = lineColumnService;
    }

    /**
     * Demo page for the custom lineNum / columnNum attributes.
     * You can open: http://localhost:8080/line-column
     */
    @GetMapping("/line-column")
    public ModelAndView lineColumnExample() {
        ModelAndView mav = new ModelAndView("line-column-example");
        mav.addObject("items", lineColumnService.getItems());
        return mav; // src/main/resources/templates/line-column-example.html
    }

    /**
     * Map root (/) to the same page for convenience.
     */
    @GetMapping("/")
    public ModelAndView index() {
        return lineColumnExample();
    }
}

