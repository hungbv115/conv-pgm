package com.process.old.service;

import java.util.List;

/**
 * Chương trình B cho phép người dùng nhập thêm thông tin.
 */
public interface ProgramBService {

    /**
     * Các dòng hiển thị ban đầu khi mở chương trình B.
     */
    List<String> buildInitialLines();

    /**
     * Xử lý dữ liệu người dùng nhập trên màn hình chương trình B.
     */
    List<String> process(String customerId, String customerName, String note);
}


