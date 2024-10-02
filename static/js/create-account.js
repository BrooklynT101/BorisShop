/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            username: '',
            firstName: '',
            surname: '',
            emailAddress: '',
            shippingAddress: '',
            password: ''
        };
    },

    methods: {
        // comma separated function declarations
        // had CGPT help me with the async method with posting, since i had a lot of issue with module interactivity
        async registerCustomer() {
            try {
                const customerData = {
                    username: this.username,
                    firstName: this.firstName,
                    surname: this.surname,
                    emailAddress: this.emailAddress,
                    shippingAddress: this.shippingAddress,
                    password: this.password
                };

                console.log('Customer to register:', customerData);

                // Send a POST request to the server to register the customer
                const response = await axios.post('/api/register', customerData);

                if (response.status === 201) {
                    alert('Account created successfully! Redirecting...');
                    window.location.href = 'signin.html';  // redirect to login page
                } else if (response.status === 401) {
                    alert('That username is already in use!');
                    window.location.href = 'create-account.html';  // redirect to login page
                }
            } catch (error) {
                alert('Error creating account: ' + error.response.data);
            }
        }
    },

    // other modules
    mixins: []

});

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// sse the Vuex store
app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");