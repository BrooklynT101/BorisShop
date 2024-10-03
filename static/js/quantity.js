/* global Vue, sessionStorage, product */

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

const app = Vue.createApp({
    computed: {
        ...Vuex.mapState({
                product: 'selectedProduct'
        })
    },
    data() {
        return {
            quantity: 1 // default quantity
        };
    },
    methods: {
        addToCart() {
            // null product handling
            if (!this.product) {
                console.error('No product selected');
                return;
            } else if (!this.product.productId) {
                console.error('Product ID for selected product did not load');
                return;
            }

            if (this.quantity <= 0) {
                alert('Quantity must be greater than 0, c\'mon man');
            } else if (this.quantity > this.product.quantityInStock) {
                alert('Not enough in stock!');
            } else {
                // call the mutation method function in vuex store (cart.js)
                sessionStore.commit("addItem", new SaleItem(this.product, this.quantity));
                window.location = 'view-products.html';
            }
            
            
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
