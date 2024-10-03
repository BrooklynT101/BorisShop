/* global Vue, axios */

"use strict";

// import data store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

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
            window.location("checkout.html");
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
// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// use vuex store
app.use(sessionStore);

// mount the page - this needs to be the last line in the file
app.mount("main");