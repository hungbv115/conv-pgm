package com.process.old.service;

import java.util.List;

/**
 * DTO kết quả xử lý bước đầu của Program C.
 * Cho phép service quyết định: tiếp tục ở màn C hay chuyển sang màn B.
 */
public class ProgramCProcessResult {

    private final boolean goToProgramB;
    private final String redirectPath;
    private final List<String> lines;

    public ProgramCProcessResult(boolean goToProgramB, String redirectPath, List<String> lines) {
        this.goToProgramB = goToProgramB;
        this.redirectPath = redirectPath;
        this.lines = lines;
    }

    /** true nếu sau khi xử lý xong cần chuyển sang màn Program B. */
    public boolean isGoToProgramB() {
        return goToProgramB;
    }

    /** Đường dẫn redirect (ví dụ: "/pgmB"), chỉ dùng khi goToProgramB = true. */
    public String getRedirectPath() {
        return redirectPath;
    }

    /** Các dòng log hiển thị ở màn C, dùng khi vẫn ở lại Program C. */
    public List<String> getLines() {
        return lines;
    }
}


