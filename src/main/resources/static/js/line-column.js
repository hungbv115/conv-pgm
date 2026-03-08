document.addEventListener('DOMContentLoaded', function () {
    const rows = document.querySelectorAll('.item-row');

    rows.forEach(function (row) {
        const lineNum = row.getAttribute('lineNum');
        const columnNum = row.getAttribute('columnNum');

        if (lineNum != null && columnNum != null) {
            // Gắn tooltip mô tả
            row.title = 'Line ' + lineNum + ', Column ' + columnNum;
            // Và log ra console cho mục đích debug
            console.log('Row:', row.textContent.trim(), '=> line:', lineNum, 'column:', columnNum);
        }
    });
});


