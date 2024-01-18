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
      cards: [],
      card: {},
      cardModal:false,
      colorSelected: "",
      typeSelected: "",
      cardStatus:true,
      cardNumber:"",

    };
  },

  created() {
    this.loadData();
    console.log("holi");
  },

  methods: {
    loadData() {
      axios.get("/api/clients/current")
        .then((response) => {
          this.data = response.data;
          console.log(this.name);
          this.accounts = this.data.accounts;
          this.name = this.data.fullName;
          this.accountDate = this.accounts.date
          this.loans = this.data.loans;
          this.cards= this.data.cards;
          this.card= this.cards.card;       

          
          
          
          console.log(this.data)
          console.log(this.cardNumber)
        })
        .catch((error) => console.log(error));
    },


    formatDate(aux){
      // Obtenengo el año y el mes de la fecha
       aux = aux.slice(2,7).replace("-","/")

      return aux.substring(3,5) + "/" + aux.substring(0,2)
     
    },


    

    createCard(){
      axios.post("/api/clients/current/cards?cardColor="+this.colorSelected+"&cardType="+this.typeSelected)
      .then(response =>{
       
        console.log(response)
        
      })
      .catch(error => console.log(error))
      
    },

   

  },
}).mount("#app");