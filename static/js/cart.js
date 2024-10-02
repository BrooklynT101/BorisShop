/* global Vue */

"use strict";

// import data store
import { sessionStore } from './session-store.js';

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
            cartItems: [], // To hold items from sessionStorage
            totalAmount: 0 // To hold the total cart amount
        };
    },

    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),

    mounted() {
        // semicolon separated statements
        this.loadCart();
    },

    methods: {
        // comma separated function declarations
        loadCart() {
            // Retrieve cart items from sessionStorage
            const items = JSON.parse(sessionStorage.getItem("items")) || [];
            this.cartItems = items;

            // Calculate the total amount
            this.totalAmount = this.cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0);
        },

        checkOut() {
            let sale = new Sale(this.customer, this.items);
            axois.post()
        }
    }

});

/* other component imports go here */

app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");