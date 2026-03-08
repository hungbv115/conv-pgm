document.addEventListener('DOMContentLoaded', function () {
    const input = document.getElementById('commandInput');
    if (input) {
        input.focus();
    }

    const logContainer = document.querySelector('.terminal-log');
    if (logContainer) {
        logContainer.scrollTop = logContainer.scrollHeight;
    }

    // Hỗ trợ phím F9 để submit lệnh như Enter
    document.addEventListener('keydown', function (event) {
        if (event.key === 'F9') {
            event.preventDefault();
            const form = input && input.form;
            if (form) {
                form.submit();
            }
        }
    });
});
