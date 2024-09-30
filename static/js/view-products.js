/* global Vue, axios */

var productsApi = '/api/products';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array()
        };
    },

    mounted() {
        // semicolon separated statements
        this.getProducts();
    },

    methods: {
        // comma separated function declarations
        getProducts() {

            // send GET request
            axios.get(productsApi)
                    .then(response => {
                        // store response in products model
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        }

//        update(category) {
////            The sessionStore.commit call is calling the selectStudent mutation passing the product.
//            sessionStore.commit('selectedCategory', category);
////            The window.location is the JavaScript equivalent of sendRedirect — it is telling the browser to load the update-product.html page.
//            window.location = 'view-products.html';
//        }

    }

//    // other modules
//    mixins: []

});

// other component imports go here

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// import the session store
//import { sessionStore } from './session-store.js';
//app.use(sessionStore);

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");