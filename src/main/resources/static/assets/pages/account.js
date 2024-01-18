const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      
  
      transactions: [],
      account:[],
      
      
    
      type:"",
    };
  },

  beforeCreate() {
    const searchId = location.search;
    const paramsId = new URLSearchParams(searchId);
    const ID = paramsId.get("id");

    

    axios("/api/clients/current" )
      .then((response) => {
        console.log(response)
        this.data = response.data; 
        this.account = response.data.accounts.find(account => account.id == ID)       
        this.transactions = this.account.transactions
        
        
      
        
        console.log(this.accounts)
        
        this.type= this.transactions.type
        console.log(this.data)
      })
      .catch((error) => console.log(error));

         

      
  },

  methods:{
//     balanceFinal(balance,amount,type){
     
// if(type = "DEBIT"){
//   balance = balance - amount
//   return balance
// }

// else{
//   balance = balance + amount
//   return balance
// }

//       }

  }
}).mount("#app");
