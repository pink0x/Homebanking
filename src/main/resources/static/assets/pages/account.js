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
      type:"",
    };
  },

  beforeCreate() {
    const searchId = location.search;
    const paramsId = new URLSearchParams(searchId);
    const ID = paramsId.get("id");

    axios("/api/clients/current")
      .then((response) => {
        this.data = response.data.accounts.find(account => account.id == ID);
        
        this.name = this.data.number;
        this.date = this.data.date;
        this.balance = this.data.balance;
        this.transactions = this.data.transactions
        
        this.type= this.transactions.type
        console.log(this.data)
      })
      .catch((error) => console.log(error));

      
  },
}).mount("#app");
