/* global Vue, sessionStorage, product */

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

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
            if (!this.product) {
                console.error('No product selected');
                return;
            }
            // call the mutation method function in vuex store (cart.js)
            sessionStore.commit("addItem", new SaleItem(this.product, this.quantity));
        }
    }
});

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

//use vuex store
app.use(sessionStore);

// mount the Vue instance
app.mount("main");
