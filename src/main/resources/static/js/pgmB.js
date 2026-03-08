document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('pgmBForm');
    const exitForm = document.getElementById('pgmBExitForm');

    const idInput = document.querySelector('input[name="customerId"]');
    const nameInput = document.querySelector('input[name="customerName"]');
    const noteInput = document.querySelector('input[name="note"]');

    // Tự focus vào ô CUST ID khi mở màn
    if (idInput) {
        idInput.focus();
    }

    const inputs = [idInput, nameInput, noteInput];

    // Enter để chuyển ô / submit khi ở ô cuối
    inputs.forEach((input, index) => {
        if (!input) return;
        input.addEventListener('keydown', function (event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                const next = inputs[index + 1];
                if (next) {
                    next.focus();
                } else if (form) {
                    form.submit();
                }
            }
        });
    });

    // F9 = Process, F3 = Exit
    document.addEventListener('keydown', function (event) {
        if (event.key === 'F9') {
            event.preventDefault();
            if (form) {
                form.submit();
            }
        } else if (event.key === 'F3') {
            event.preventDefault();
            if (exitForm) {
                exitForm.submit();
            }
        }
    });
});


