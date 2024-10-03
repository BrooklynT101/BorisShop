export const navigationMenu = {
    computed: {
        signedIn() {
            return this.customer !== null;
        },
        ...Vuex.mapState({
                customer: 'customer'
        })
    },

    template: `
    <nav class="navmenu">
        <div class="welcome-message" v-if="signedIn"><p>Welcome {{customer.firstName}}</p></div>
        <div class="nav-links">    
            <button @click="navigateTo('.')">Home</button>
            <button v-if="signedIn" @click="navigateTo('view-products.html')">Browse Products</button>
            <button v-if="signedIn" @click="navigateTo('cart.html')">View Cart</button>
            <button v-if="signedIn" @click="signOut()">Sign Out</button>
            <button v-if="!signedIn" @click="navigateTo('sign-in.html')">Sign In</button>
            <button v-if="!signedIn" @click="navigateTo('create-account.html')">Create an Account</button>
        </div>    
    </nav>
  `,

    methods: {

        navigateTo(url) {
            window.location.href = url;
        },

        signOut() {
            sessionStorage.removeItem('selectedProduct');
            sessionStorage.removeItem('items');
            sessionStorage.clear();
            this.$store.commit('signIn', null); // Clear customer state if needed
            window.location = '.'; // Redirect after signing out
        }
    }
};
