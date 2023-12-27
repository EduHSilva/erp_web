export default {
  methods: {
    formatDate(date) {
      let newDate = new Date(date)
      return newDate.toLocaleDateString();
    },
    formatStatus(status, $t) {
      switch (status) {
        case "ACTIVE":
          return $t("active")
      }
    }
  }
}