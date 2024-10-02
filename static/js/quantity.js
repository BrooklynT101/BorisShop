/* global Vue, sessionStorage, product */

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

//console.log('Selected Product:', product);  // Check if this is null

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
//            console.log('addToCart Method Triggered');
//            error checking
            if (!this.product) {
                console.error('No product selected');
                return;
            }

//cloning the cart items to remove vue proxy and circular referencing
            let cartItems = JSON.parse(JSON.stringify(sessionStore.state.items || [])); // Retrieve current cart or initialize an empty array
//            const cartItems = sessionStore.state.items || []; // Retrieve current cart or initialize an empty array

            // Check if the product already exists in the cart
            const existingItem = cartItems.find(item => item.productId === this.product.productId);
            if (existingItem) {
                existingItem.quantity += parseInt(this.quantity, 10); // Update quantity if already in the cart
            } else {
                // Add the new item to the cart
                cartItems.push({
                    productId: this.product.productId,
                    name: this.product.name,
                    price: this.product.listPrice,
                    quantity: parseInt(this.quantity, 10)
                });
            }

// Clone the updated cart items to remove the Vue Proxy
            const cleanCartItems = JSON.parse(JSON.stringify(cartItems));

            console.log('Cart Items:', cartItems);
            console.log('Clean Cart Items:', cleanCartItems);
            console.log('Product to be added:', this.product);

            // commit clean cart to Vuex state
            sessionStore.commit('updateCart', cleanCartItems);
            console.log(`Added ${this.quantity} of ${this.product.name} to the cart.`);

//            window.location = 'view-products.html';
        }
    }
});

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

app.use(sessionStore);

// mount the Vue instance
app.mount("main");
