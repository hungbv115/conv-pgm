package com.process.old.service;

import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * Service quản lý log theo từng màn hình dựa trên HttpSession.
 */
public interface ScreenLogService {

    String SCREEN_CMD_MAIN = "CMD_MAIN";
    String SCREEN_PGM_A = "PGM_A";
    String SCREEN_PGM_B = "PGM_B";
    String SCREEN_PGM_C = "PGM_C";

    /**
     * Lấy (hoặc tạo mới) danh sách log cho một màn hình.
     */
    List<String> getOrCreateLog(HttpSession session, String screenId);

    /**
     * Xóa hết nội dung log nhưng giữ lại đối tượng List (dùng cho CLEAR ở cmdMain).
     */
    void clearLog(HttpSession session, String screenId);

    /**
     * Xóa hẳn log của màn hình khỏi session (dùng cho trường hợp CANCEL).
     */
    void removeLog(HttpSession session, String screenId);
}


