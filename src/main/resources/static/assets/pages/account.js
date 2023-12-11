const { createApp } = Vue

let app = createApp({
    data() {
        return {
            data: [],
            name: "",
            lastName: "",
            email: "",
            accounts: [],


        }
    },


    beforeCreate() {
        const searchId = location.search
        const paramsId = new URLSearchParams(searchId)
        const ID = paramsId.get('id')
        console.log("/api/accounts/" + ID)
      
       
    },






    created() {
        this.loadData()
        console.log("holi")
    },

    methods: {
        loadData() {
            axios("/api/clients/1")
                .then(response => {
                    this.data = response.data;
                    console.log(this.data)
                    this.accounts = this.data.accounts
                        ;

                })
                .catch(error => console.log(error))
        },


    }

})

    .mount("#app")