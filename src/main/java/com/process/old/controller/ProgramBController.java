package com.process.old.controller;

import com.process.old.service.ProgramBService;
import com.process.old.service.ScreenLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProgramBController {

    private final ProgramBService programBService;

    private final ScreenLogService screenLogService;

    public ProgramBController(ProgramBService programBService, ScreenLogService screenLogService) {
        this.programBService = programBService;
        this.screenLogService = screenLogService;
    }

    @GetMapping("/pgmB")
    public ModelAndView showProgramB(
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "orderId", required = false) String orderId,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "note", required = false) String note, HttpSession session) {
        List<String> lines = screenLogService.getOrCreateLog(session, ScreenLogService.SCREEN_CMD_MAIN);
        lines.addAll(programBService.buildInitialLines());
        ModelAndView mav = new ModelAndView("pgmB");
        mav.addObject("programLines", lines);
        mav.addObject("programName", "PROGRAM B");

        // Thông tin bối cảnh nếu được gọi từ Program C
        mav.addObject("from", from);
        mav.addObject("orderId", orderId);
        mav.addObject("customerId", customerId);
        mav.addObject("customerName", customerName);
        mav.addObject("note", note);

        return mav;
    }

    @PostMapping("/pgmB")
    public ModelAndView handleProgramB(
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "orderId", required = false) String orderId,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "note", required = false) String note, HttpSession session) {

        List<String> lines = screenLogService.getOrCreateLog(session, ScreenLogService.SCREEN_CMD_MAIN);
        lines.addAll(programBService.process(customerId, customerName, note));

        // Nếu được gọi từ Program C thì sau khi xử lý xong quay lại màn C (afterRunB)
        if ("pgmC".equalsIgnoreCase(from)) {
            StringBuilder redirect = new StringBuilder("/pgmC/afterRunB");
            redirect.append("?orderId=").append(orderId == null ? "" : orderId);
            redirect.append("&customerId=").append(customerId == null ? "" : customerId);
            redirect.append("&customerName=").append(customerName == null ? "" : customerName);
            redirect.append("&note=").append(note == null ? "" : note);
            return new ModelAndView("redirect:" + redirect);
        }

        // Ngược lại, hiển thị lại màn Program B như bình thường
        ModelAndView mav = new ModelAndView("pgmB");
        mav.addObject("programLines", lines);
        mav.addObject("programName", "PROGRAM B");

        mav.addObject("from", from);
        mav.addObject("orderId", orderId);

        // Giữ lại giá trị người dùng đã nhập
        mav.addObject("customerId", customerId);
        mav.addObject("customerName", customerName);
        mav.addObject("note", note);

        return mav;
    }

    @PostMapping("/pgmB/exit")
    public ModelAndView exitProgramB(
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "orderId", required = false) String orderId,
            RedirectAttributes redirectAttributes) {

        // Nếu được gọi từ C thì thoát về lại màn C
        if ("pgmC".equalsIgnoreCase(from)) {
            return new ModelAndView("redirect:/pgmC");
        }

        redirectAttributes.addFlashAttribute("resultMessage", "Returned from Program B.");
        return new ModelAndView("redirect:/cmd");
    }
}

