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
        return {};
    },
    computed: {
        ...Vuex.mapState({
                items: 'items',
                customer: 'customer'
        }),
        totalAmount() {
            return this.items.reduce((total, item) => total + (item.salePrice * item.quantityPurchased), 0);
        }
    },

    mounted() {
    },

    methods: {

        // Call the mutation via the usual session commit call in the 'then' callback of the Axios request.
        checkOut() {
            //need to complete this -  It should: 
            // - create a Sale domain object
            // - POST the sale object to the /api/sales path of the web service
            // - then call clearItems()
//            let sale = new Sale(this.customer, this.items);
//            axois.post()
        },

        // clear cart items
        clearItems() {
            console.log('Cart cleared');
            sessionStore.commit('updateCart', []);
        },

        addItem(item) {
            if (!item) {
                console.error('Tried to add an invalid item to cart');
                return;
            }
            // commit to the session
            sessionStore.commit('addItem', item);
        },

        removeItem(productId) {
            if (!productId) {
                console.error('Tried to delete a cart item with an invalid product ID!');
                return;
            }
            sessionStore.commit('removeItem', productId);
        }
    }

});
/* other component imports go here */

// use vuex store
app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");