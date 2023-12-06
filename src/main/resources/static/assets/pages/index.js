const { createApp } = Vue

let app = createApp({
    data() {
        return {
            data: [],
            name: "",
            lastName: "",
            email: "",

        }
    },

    created() {
        this.loadData()
    },

    

    methods: {
        loadData() {
            axios("/clients")
                .then(response => {
                    this.data = response.data._embedded.clients;
                    console.log(this.data)
                    ;
                
                })
                .catch(error => console.log(error))
        },

        createClient (event){
            event.preventDefault ()
            const newClient = {
                "name" : this.name,
              "lastName" : this.lastName,
              "email" : this.email
            }
             axios.post ("/clients", newClient)
              .then (response=> {
                this.data = response
                this.loadData()
              })
              .catch(error => console.log(error))
        }
    }

})

    .mount("#app")