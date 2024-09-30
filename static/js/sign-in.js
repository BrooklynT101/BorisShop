/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            customer: {email: '', password: ''} // Bind customer login form
        };
    },
    mounted() {
// semicolon separated statements

        alert('Mounted method called');
    },
    methods: {
        // comma separated function declarations
        signInCustomer() {
            axios.post('/api/customers/signin', this.customer)
                    .then(response => {
                        // get customer 
                        const customer = response.data;

// 
                        if (customer) {
                            // store customer in session
                            sessionStore.commit('SignInCustomer', customer);

                            // debug alert
                            alert('User Signed In!');
                            window.location = 'index.html';
                        } else {
                            alert('Wrong Username/Password Combination');
                        }
                    })
                    .catch(error => {
                        console.error(error);  // Log the error for debugging
                        alert('An error occurred during login. Please try again.');
                    });
        }
    }
    ,
    // other modules
    mixins: []

}
);
// other component imports go here


// mount the page - this needs to be the last line in the file
app.mount("main");