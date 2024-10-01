/* global Vue, axios */


// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

const app = Vue.createApp({

    data() {
        return {
            customer: {username: '', password: ''} // Bind customer login form
        };
    },
    methods: {
        // comma separated function declarations
        signIn() {
            axios.post('/api/signin', this.customer)
                    .then(response => {
                        // get customer 
                        const customer = response.data;

                        // store customer in session
                        sessionStore.commit('signIn', customer);
                        alert('User Signed In!');
                        window.location = 'index.html';

                    })
                    .catch(error => {
                        if (error.response) {
                            // The request was made and the server responded with a status code
                            // that falls out of the range of 2xx
                            if (error.response.status === 401) {
                                alert('Wrong Username/Password Combination');
                            } else {
                                alert('An error occurred: ' + error.response.data.message);
                            }
                        } else {
                            // Something happened in setting up the request that triggered an Error
                            alert('An error occurred during login. Please try again.');
                        }
                    });
        }
    },

    mounted() {
        alert('Mounted method called');
    }
//    mixins: []

}
);

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// sse the Vuex store
app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");