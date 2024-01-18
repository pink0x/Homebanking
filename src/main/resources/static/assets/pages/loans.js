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
      client:{},
      clientRole:"",
      modal:false,

      loanName:"",
      loanMaxAmount:"",
      loanPayments: "",
      
      loanInterest: "",
      
      interest: "",

      newLoan:{},

      loan: {},








    };
  },

  created() {
    this.loadData();
    this.getClient(),
    console.log("holi");
    
      

  },

  methods: {



  getClient(){
    axios.get("/api/clients/current")
    .then((response)=>{
      this.client = response.data;
      this.clientRole = this.client.roleType
      this.accounts = this.client.accounts
      console.log(this.client)
      console.log(this.clientRole)
    })
    .catch((error) => console.log(error));
  },

  showModal(){
    if(!this.modal){
      this.modal = true
    }
    else {
      this.modal = false
    }
    
  },
  loadData() {
    axios.get("/api/loans")
      .then((response) => {
        this.loans = response.data;
        
        
        
        

        
      })
      .catch((error) => console.log(error));
  },

 
   
  createLoan (){
    const loanPaymentsArray = this.loanPayments.split(',').map(Number);

    axios.post('/api/loans/create?name='+this.loanName+'&maxAmount='+this.loanMaxAmount+'&payments='+loanPaymentsArray+'&interest='+this.loanInterest)

    .then((response)=>{
      this.newLoan = response.data;
      console.log(this.newLoan)
      console.log(loanPaymentsArray)
    })

    .catch((error) => console.log(error));

  },


    
    blbl(){
        console.log(this.loanSelected.id)
    },

   

    createClientLoan(){
        console.log(this.loanSelected.id)
        console.log(this.accountSelected)
        console.log(this.amountSelected)
        console.log(this.paymentSelected)
        const fedeVal = {"id": this.loanSelected.id,
        "amount": this.amountSelected,
        "payments": this.paymentSelected,
         "accountNumber": this.accountSelected}
        
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