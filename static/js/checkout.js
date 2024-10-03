/* global Vue, sessionStorage, product, axios */

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// import the number formatter
import { NumberFormatter } from './number-formatter.js';

class SaleItem {
    constructor(product, quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.listPrice;
    }
}

class Sale {
    constructor(customer, items, saleTotal) {
        this.customer = customer;
        this.items = items;
        this.saleTotal = saleTotal;
    }
}

const app = Vue.createApp({

    data() {
        return {
            shippingCost: 5.0
        };
    },
    computed: {
        ...Vuex.mapState({
                items: 'items',
                customer: 'customer'
        }),
        productTotal() {
            return this.items.reduce((total, item) => total + (item.salePrice * item.quantityPurchased), 0);
        }
    },
    methods: {
        confirmCheckout() {
            // - create a Sale domain object 
            let sale = new Sale();
            // set values
            sale.customer = this.customer;
            sale.items = this.items;
            sale.saleTotal = this.productTotal + this.shippingCost;

            // - POST the sale object to the /api/sales path of the web service
            axios.post('api/sales/', sale)
                    // - then call clearItems()
                    .then(response => {
//                        alert("Checkout successful! Order placed.");
                        this.clearItems(); // Clear cart after checkout
                        window.location = 'order-confirmation.html'; // Redirect after checkout
                    })
                    .catch(error => {
                        console.error('Error during checkout:', error.response);
                        alert('Error during checkout: ' + error.response);
                    });
        },

        clearItems() {
            console.log("Items were cleared from cart from checkout");
            sessionStore.commit('updateCart', []);
        }
    },
    mixins: [NumberFormatter]
});

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

//use vuex store
app.use(sessionStore);

// mount the Vue instance
app.mount("main");
