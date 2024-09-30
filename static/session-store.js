export const sessionStore = Vuex.createStore({

    state() {
        return {
            currCustomer: null
        };
    },

    mutations: {

        signInCustomer(state, customer) {
            state.currCustomer = customer;
        },
        signOutCustomer(state) {
            state.currCustomer = null;
        }

    },

    // Persist the state using session storage (ensures customer session across pages)
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});