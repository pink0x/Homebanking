const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      balance: "",
      date: "",
      transactions: [],
    //   transaction:{},
    //   type:"",
    };
  },

  beforeCreate() {
    const searchId = location.search;
    const paramsId = new URLSearchParams(searchId);
    const ID = paramsId.get("id");

    axios("/api/accounts/" + ID)
      .then((response) => {
        this.data = response.data;
        this.name = this.data.number;
        this.date = this.data.date;
        this.balance = this.data.balance;
        this.transactions = this.data.transactions
        // this.transaction=this.transactions.transaction
        // this.type= this.transaction.type
        console.log(this.transaction)
      })
      .catch((error) => console.log(error));

      
  },
}).mount("#app");
