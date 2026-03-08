package com.process.old.service;

import java.util.List;

/**
 * Chương trình C, trong logic có gọi xử lý chương trình B,
 * sau đó quay lại C để tiếp tục nghiệp vụ.
 */
public interface ProgramCService {

    /**
     * Các dòng hiển thị ban đầu khi mở chương trình C.
     */
    List<String> buildInitialLines();

    /**
     * Xử lý dữ liệu nhập cho chương trình C. Kết quả trả về là DTO cho biết
     * nên hiển thị tiếp màn C hay chuyển sang màn B.
     */
    ProgramCProcessResult process(String orderId, String customerId, String customerName, String note);

    /**
     * Phần xử lý "sau khi đã chạy xong màn B", được gọi từ controller riêng.
     * Ở đây giả định Program B đã xử lý/validate dữ liệu khách hàng xong.
     */
    List<String> medAfterRun(String orderId, String customerId, String customerName, String note);
}


