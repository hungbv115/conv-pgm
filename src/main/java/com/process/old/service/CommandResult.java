package com.process.old.service;

/**
 * Kết quả sau khi xử lý một lệnh từ màn hình CMD.
 */
public class CommandResult {

    private final String redirectPath;   // ví dụ: "/cmd" hoặc "/pgmA"
    private final String resultMessage;  // nội dung hiển thị ở dòng LAST:
    private final String lastCommand;    // dùng để fill lại input

    public CommandResult(String redirectPath, String resultMessage, String lastCommand) {
        this.redirectPath = redirectPath;
        this.resultMessage = resultMessage;
        this.lastCommand = lastCommand;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getLastCommand() {
        return lastCommand;
    }
}


