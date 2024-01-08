const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      loans: [],
      accounts:[],
      accountSelected:"",
      loanSelected: "",
      paymentSelected:"",
      amountSelected:"",
      id: "",

      
      
      

    };
  },

  created() {
    this.loadData();
    console.log("holi");
    this.clientData();

    
  },

  methods: {
    loadData() {
      axios.get("/api/loans")
        .then((response) => {
          this.loans = response.data;
         
          
          
          
         
          console.log(this.data)
          console.log(this.loans)
        })
        .catch((error) => console.log(error));
    },
    blbl(){
        console.log(this.loanSelected.id)
    },

    clientData(){
      axios.get("/api/clients/current")
        .then((response) => {
          this.data = response.data;
          this.accounts = this.data.accounts;
          console.log(this.data)
        })
        .catch((error) => console.log(error));
    },

    createLoan(){
        console.log(this.loanSelected.id)
        console.log(this.accountSelected)
        console.log(this.amountSelected)
        console.log(this.paymentSelected)
        const fedeVal = {"id": this.loanSelected.id,
        "amount": this.amountSelected,
        "payments": this.paymentSelected,
         "accountNumber": this.accountSelected} 
        //{Id:this.loanSelected.id,
        // AccountNumber:this.accountSelected,
        // amount:this.amountSelected,
        // payments:this.paymentSelected}
        axios.post('/api/loans',fedeVal)
        .then((response) => {
          console.log(response)
        })
        .catch((error) => console.log(error));

    },


    formatDate(aux){
      // Obtenengo el año y el mes de la fecha
       aux = aux.slice(2,7).replace("-","/")

      return aux.substring(3,5) + "/" + aux.substring(0,2)
     
    },

    
    
  },
}).mount("#app");