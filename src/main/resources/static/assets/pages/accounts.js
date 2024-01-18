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
      accountNumber:"",
      modal:false,
      modalCreate:false,

      loanID: "",
      accountNumber: "",

      accountType:"",
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
          this.accountNumber = this.accounts.number
          console.log(this.data)
          console.log(this.accountType)
        })
        .catch((error) => console.log(error));
    },
    showModal(){
      this.modal = true
      console.log("holus")

    },

    showModalCreate(){
      this.modalCreate =true
      console.log(this.accountType)
    },

    payLoan(id){
      axios.patch ("/api/loans?Id=" + id + "&number=" + this.accountNumber)
      .then(response =>{
       
        console.log(response)
      })
      .catch(error => console.log(error))
    },
    changeStatus(status){
      if (status == true) {
        this.status = false
      }

      
    },

    createAccount(){
      axios.post("/api/accounts/create?accountType="+this.accountType)
        .then(response =>{

          this.changeStatus(this.status);
          
          
  
          console.log(this.status)
          console.log(response)
        })
        .catch(error => console.log(error))
      
      },
    

    deleteAccount(accountNumber){
      axios.patch("/api/accounts?number=" + accountNumber)
      .then(response =>{

        this.changeStatus(this.status);
        
        

        console.log(this.status)
        console.log(response)
      })
      .catch(error => console.log(error))
    }

    
  },
}).mount("#app");
