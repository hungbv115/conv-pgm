package com.process.old.service;

import java.util.List;

/**
 * Service chịu trách nhiệm parse và xử lý các lệnh từ màn hình CMD.
 */
public interface CommandService {

    /**
     * Xử lý một lệnh, cập nhật logLines tương ứng và trả về kết quả điều hướng + message.
     */
    CommandResult execute(String command, List<String> logLines);
}


