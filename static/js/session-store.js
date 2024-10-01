export const sessionStore = Vuex.createStore({

    state() {
        return {
            // signed in customer
            customer: null,
            // the shopping cart items
            items: null
        };
    },
    mutations: {
        // user signs in
        signIn(state, customer) {
            state.customer = customer;
            state.items = new Array();
        }

    },
    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});