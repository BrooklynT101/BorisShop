/* global Vue, axios */

var productsApi = '/api/products';
const categoriesApi = '/api/categories';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array(),
            filteredProducts: new Array(),
            categories: new Array()
        };
    },
    mounted() {
        // semicolon separated statements
        this.getProducts();
        this.getCategories();
    },
    methods: {
        // comma separated function declarations
        getProducts() {

            // send GET request
            axios.get(productsApi)
                    .then(response => {
                        // store response in products model
                        this.products = response.data;
                        this.filteredProducts = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },

        getCategories() {
            axios.get(categoriesApi)
                    .then(response => {
                        this.categories = response.data; // store distinct categories
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred while fetching categories.");
                    });
        },

        filterProducts(category) {
            // if nothing or ALL is selected show everything, 
            if (category === 'ALL' || category === null) {
                this.filteredProducts = this.products;
//                otherwise filter by the category string
            } else {
                this.filteredProducts = this.products.filter(product => product.category === category); // Filter by category
            }
        }

    }

});
// other component imports go here

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);
// mount the page - this needs to be the last line in the file
app.mount("main");