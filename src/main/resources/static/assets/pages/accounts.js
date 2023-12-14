const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      email: "",
      accounts: [],
      accountDate: "",
      loans: [],
    };
  },

  created() {
    this.loadData();
    console.log("holi");
  },

  methods: {
    loadData() {
      axios("/api/clients/1")
        .then((response) => {
          this.data = response.data;
          console.log(this.name);
          this.accounts = this.data.accounts;
          this.name = this.data.fullName;
          this.accountDate = this.accounts.date
          this.loans = this.data.loans;
          console.log(this.data)
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");
