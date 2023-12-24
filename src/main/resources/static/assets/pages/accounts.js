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

    logout(){

      axios.post("/api/logout")
        .then(response => {
          console.log(response)
          window.location.href="../../index.html"
        })
        .catch(error => console.log("Error", error))
    },
    loadData() {
      axios("/api/clients/current")
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
