/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            customer: {email: '', password: ''}, // Bind customer login form
        };
    },

    mounted() {
        // semicolon separated statements

        alert('Mounted method called');

    },

    methods: {
        // comma separated function declarations

    },

    // other modules
    mixins: []

});

// other component imports go here


// mount the page - this needs to be the last line in the file
app.mount("main");