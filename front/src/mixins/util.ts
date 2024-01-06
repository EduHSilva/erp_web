export default {
    methods: {
        formatDate(date: Date) {
            let newDate = new Date(date)
            return newDate.toLocaleDateString();
        },
        formatStatus(status: string, $t: any) {
            switch (status) {
                case "ACTIVE":
                    return $t("active")
                case "INACTIVE":
                    return $t("inactive")
            }
        },
        showToastError() {
            let toast = document.getElementById("toast-error");
            if (toast !== null) {
                toast.classList.add("show");
                setTimeout(function () {
                    if (toast != null) toast.classList.remove("show");
                }, 3000);
            }
        },
        showToastSuccess(callback: Function | null) {
            let toast = document.getElementById("toast-success");
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