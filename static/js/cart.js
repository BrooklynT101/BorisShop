"use strict";

class SaleItem {
	constructor(product, quantityPurchased) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = product.listPrice;
	}
}

class Sale {
	constructor(customer, items) {
		this.customer = customer;
		this.items = items;
	}
}

const app = Vue.createApp({

    data() {
        return {
            // models (comma separated key/value pairs)

        };
    },

    computed: Vuex.mapState({
	    product: 'selectedProduct',
	    items: 'items',
	    customer: 'customer'
    }),


    mounted() {
        // semicolon separated statements


    },

    methods: {
        // comma separated function declarations

    }

});

/* other component imports go here */

// import data store
import { sessionStore } from './session-store.js'
app.use(sessionStore);	

// mount the page - this needs to be the last line in the file
app.mount("main");