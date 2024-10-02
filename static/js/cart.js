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
            totalAmount: 0 // to hold the total cart amount
        };
    },
    computed: {
        ...Vuex.mapState({
                items: 'items',
                customer: 'customer',
                totalAmount() {
                        return this.items.reduce((total, item) => total + (item.price * item.quantity), 0);
                }
        })
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
            this.items = [];
            sessionStore.commit('updateCart', this.items);
        },

        addItem(item) {
            const checkItem = this.items.find(i => i.productId === item.productId);
            //if check exists update the item quantity
            if (checkItem) {
                checkItem.quantity += item.quantity;
            }
            //else push the item
            else {
                this.items.push(item);
            }

//            commit to the session
            sessionStore.commit('updateCart', this.items);
        }
    }

});
/* other component imports go here */

// use vuex store
app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");