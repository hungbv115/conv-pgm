package com.process.old.controller;

import com.process.old.service.ProgramCProcessResult;
import com.process.old.service.ProgramCService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProgramCController {

    private final ProgramCService programCService;

    public ProgramCController(ProgramCService programCService) {
        this.programCService = programCService;
    }

    @GetMapping("/pgmC")
    public ModelAndView showProgramC() {
        List<String> lines = programCService.buildInitialLines();
        ModelAndView mav = new ModelAndView("pgmC");
        mav.addObject("programLines", lines);
        mav.addObject("programName", "PROGRAM C");
        return mav;
    }

    @PostMapping("/pgmC")
    public ModelAndView handleProgramC(
            @RequestParam(name = "orderId", required = false) String orderId,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "note", required = false) String note) {

        ProgramCProcessResult result = programCService.process(orderId, customerId, customerName, note);

        // Nếu service yêu cầu chuyển sang màn Program B thì controller sẽ điều hướng
        if (result.isGoToProgramB()) {
            // Có thể truyền thêm tham số nếu cần (orderId, customerId, ...) bằng query string
            StringBuilder redirectPath = new StringBuilder(result.getRedirectPath());
            redirectPath.append("?from=pgmC");
            if (orderId != null) {
                redirectPath.append("&orderId=").append(orderId);
            }
            if (customerId != null) {
                redirectPath.append("&customerId=").append(customerId);
            }
            if (customerName != null) {
                redirectPath.append("&customerName=").append(customerName);
            }
            if (note != null) {
                redirectPath.append("&note=").append(note);
            }
            return new ModelAndView("redirect:" + redirectPath);
        }

        // Ngược lại, hiển thị lại màn C với các dòng log từ DTO
        ModelAndView mav = new ModelAndView("pgmC");
        mav.addObject("programLines", result.getLines());
        mav.addObject("programName", "PROGRAM C");

        mav.addObject("orderId", orderId);
        mav.addObject("customerId", customerId);
        mav.addObject("customerName", customerName);
        mav.addObject("note", note);

        return mav;
    }

    /**
     * Endpoint riêng để gọi phần xử lý "sau khi đã chạy xong màn hình B".
     * Controller khác (ví dụ ProgramBController) có thể redirect sang đây
     * sau khi hoàn tất xử lý trong Program B.
     */
    @GetMapping("/pgmC/afterRunB")
    public ModelAndView afterRunFromB(
            @RequestParam(name = "orderId", required = false) String orderId,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "note", required = false) String note) {

        List<String> lines = programCService.medAfterRun(orderId, customerId, customerName, note);
        ModelAndView mav = new ModelAndView("pgmC");
        mav.addObject("programLines", lines);
        mav.addObject("programName", "PROGRAM C");

        mav.addObject("orderId", orderId);
        mav.addObject("customerId", customerId);
        mav.addObject("customerName", customerName);
        mav.addObject("note", note);

        return mav;
    }

    @PostMapping("/pgmC/exit")
    public ModelAndView exitProgramC(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("resultMessage", "Returned from Program C.");
        return new ModelAndView("redirect:/cmd");
    }
}

