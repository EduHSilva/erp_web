export default {
    methods: {
        formatStatus(status: string, $t: any) {
            switch (status) {
                case "ACTIVE":
                    return $t("active")
                case "INACTIVE":
                    return $t("inactive")
                case "PENDENT":
                    return $t("pendent")
            }
        },
        showToastError() {
            const toast = document.getElementById("toast-error");
            if (toast !== null) {
                toast.classList.add("show");
                setTimeout(function () {
                    if (toast != null) toast.classList.remove("show");
                }, 3000);
            }
        },
        showToastSuccess(callback: Function | null) {
            const toast = document.getElementById("toast-success");
            if (toast !== null) {
                toast.classList.add("show");

                setTimeout(function () {
                    if (toast != null) toast.classList.remove("show");
                    if (callback != null) callback();
                }, 2000);
            }
        }
    }
}