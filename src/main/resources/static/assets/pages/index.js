const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      registered: false,
      email: "",
      password: "",
      error: "",
      blankInputError: "",
      
    };
  },

  created() {
    
    console.log(this.registered);
  },

  methods: {
    login(){
      axios.post("/api/login?email=" +this.email +"&password="+this.password)
        .then(response => {           
            window.location.href="./assets/pages/accounts.html"
            console.log(response)
          
          this.clearData()
          
        })
        .catch(error => {
          this.error = "Incorrect username or password"
        console.log("Error", error)       

        })
    },

    register(){
      axios.post("/api/clients?firstName="+this.name+"&lastName="+this.lastName+"&email="+this.email+"&password="+this.password)
        .then(response => {
          console.log(response)
          this.login()
        })
        .catch(response => 
          this.blankInputError = response.response.data,
          console.log("Error"))
    },

    registrado(){
        this.registered= !this.registered
    },


   
clearData (){
    this.email = "",
    this.password = "",
    this.name = "",
    this.lastName = ""
}

  },
}).mount("#app");
