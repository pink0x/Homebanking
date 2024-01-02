const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      accountNumberOrigin: "",
      accountNumberDestiny:"",
      amount:"",
      description : "",
      showModal: false,
      balance:"",


      accounts: [],
      


    };
  },

  created() {
    this.loadData();
    console.log("holi");
  },

  methods: {

    logout() {

      axios.post("/api/logout")
        .then(response => {
          console.log(response)
          window.location.href = "../../index.html"
        })
        .catch(error => console.log("Error", error))
    },
    loadData() {
      axios("/api/clients/current")
        .then((response) => {
          this.data = response.data;
          this.accounts = this.data.accounts;
          this.amount = 

          console.log(this.accounts)
        })
        .catch((error) => console.log(error));
    },
    transaction() {
      axios.post("/api/transactions?amount="+this.amount+ "&description=" +this.description+"&accountNumberOrigin="+this.accountNumberOrigin+"&accountNumberDestiny="+this.accountNumberDestiny)
      
        .then((response) => {
          this.data = response.data;

          console.log(this.data)
        })
        .catch((error) => console.log(error));
    },

    modalVisible(){
      this.showModal = true
    },

    modalHiden(){
      this.showModal= false
    },

  },

  computed: {

    showBalance() {
      let account = {... this.accounts.find(account => account.number == this.accountNumberOrigin) }

      this.balance = account.balance?.toLocaleString( { style: "currency", currency: "USD" })
      console.log(this.balance)

     
    }

    




  },
}).mount("#app");



// axios.post/api/transactions?transactionAmount=${this.amount}&transactionDescription=${this.description}&originAccountNumber=${this.originAccountNumber}&destinationAccountNumber=${this.destinationAccountNumber})