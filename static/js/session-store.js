export const sessionStore = Vuex.createStore({

    state() {
        return {
// signed in customer
            customer: null,
            // the shopping cart items
            items: [],
            // selected product
            selectedProduct: null
        };
    },
    mutations: {
        // user signs in
        signIn(state, customer) {
            state.customer = customer;
            state.items = new Array();
        },
        // user selects a product
        selectProduct(state, product) {
            state.selectedProduct = product;
        },
        
        addItem(state, item) {
            state.items.push(item);
        },
        // add item to cart
        addProduct(state, product) {
            state.items.push(product);
        },
        //update cart items
        updateCart(state, items) {
            state.items = items;
        }

    },
    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});