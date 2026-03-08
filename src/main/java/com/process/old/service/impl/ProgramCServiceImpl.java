package com.process.old.service.impl;

import com.process.old.service.ProgramBService;
import com.process.old.service.ProgramCProcessResult;
import com.process.old.service.ProgramCService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramCServiceImpl implements ProgramCService {

    @SuppressWarnings("unused")
    private final ProgramBService programBService;

    public ProgramCServiceImpl(ProgramBService programBService) {
        this.programBService = programBService;
    }

    @Override
    public List<String> buildInitialLines() {
        List<String> lines = new ArrayList<>();
        lines.add("PROGRAM C - ORDER PROCESSING SCREEN");
        lines.add("-----------------------------------");
        lines.add("Enter order and customer information, then press F9 to process.");
        lines.add("Program C will internally call Program B for customer validation.");
        return lines;
    }

    @Override
    public ProgramCProcessResult process(String orderId, String customerId, String customerName, String note) {
        List<String> lines = new ArrayList<>();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        lines.add("PROGRAM C START  " + now);
        lines.add("  ORDER ID       : " + (orderId == null ? "" : orderId.trim()));
        // Ở bước đầu, Program C quyết định chuyển người dùng sang màn Program B
        // để nhập/validate thông tin khách hàng. Controller sẽ đọc DTO này và
        // thực hiện redirect sang /pgmB, thay vì render lại pgmC.
        return new ProgramCProcessResult(true, "/pgmB", lines);
    }

    @Override
    public List<String> medAfterRun(String orderId, String customerId, String customerName, String note) {
        List<String> lines = new ArrayList<>();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        lines.add("PROGRAM C AFTER RUN (FROM B)  " + now);
        lines.add("  ORDER ID       : " + (orderId == null ? "" : orderId.trim()));
        lines.add("  (Assuming customer was validated in Program B)");

        appendAfterRunLines(lines);
        return lines;
    }

    /**
     * Phần xử lý nghiệp vụ C sau khi đã "CALL" Program B xong.
     * Được tách riêng ra để dễ bảo trì/mở rộng và dùng chung.
     */
    protected void appendAfterRunLines(List<String> lines) {
        lines.add("  PROGRAM C BUSINESS STEP 1: Create order header.");
        lines.add("  PROGRAM C BUSINESS STEP 2: Link customer to order.");
        lines.add("  PROGRAM C BUSINESS STEP 3: Mark order as PENDING.");
        lines.add("  PROGRAM C COMPLETED SUCCESSFULLY.");
    }
}


