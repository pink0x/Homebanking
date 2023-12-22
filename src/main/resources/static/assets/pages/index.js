const { createApp } = Vue;

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      registered: false,
      email: "",
      password: ","
      
    };
  },

  created() {
    
    console.log(this.registered);
  },

  methods: {
    login() {
      axios.post("/api/login?email= + this.email + &password=" + this.password)
        .then((response) => {
          console.log(response)
          this.clearData()
          
        })
        .catch((error) => console.log(error));
    },

    registrado(){
        this.registered= !this.registered
    },


    register(){
        axios.post("api/clients?firstName=" + this.name + "&lastName=" + this.lastName, + "&email=" + this.email + "&password=" + this.password)
        console.log(response)
        this.clearData()
           
       

        .then (response => {
            console.log(response)
            this.clearData()
        })

        .catch (response => {
            console.log(response)
        })  

    },

clearData (){
    this.email = "",
    this.password = "",
    this.name = "",
    this.lastName = ""
}

  },
}).mount("#app");
