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
    }
  }
}