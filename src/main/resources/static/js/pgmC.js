document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('pgmCForm');
    const exitForm = document.getElementById('pgmCExitForm');

    const orderInput = document.querySelector('input[name="orderId"]');
    const custIdInput = document.querySelector('input[name="customerId"]');
    const custNameInput = document.querySelector('input[name="customerName"]');
    const noteInput = document.querySelector('input[name="note"]');

    if (orderInput) {
        orderInput.focus();
    }

    const inputs = [orderInput, custIdInput, custNameInput, noteInput];

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


