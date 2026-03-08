package com.process.old.service;

import java.util.List;

/**
 * Mô phỏng chương trình A trên hệ thống AS400.
 */
public interface ProgramAService {

    /**
     * Thực thi chương trình A và trả về các dòng log kết quả.
     */
    List<String> executeProgramA();
}


