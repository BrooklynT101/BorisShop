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
    <nav>
      <div v-if="signedIn">Welcome {{customer.firstName}}</div>
      <a href=".">Home</a>
      <a href="view-products.html" v-if="signedIn">Browse Products</a>
      <a href="cart.html" v-if="signedIn">View Cart</a>
      <a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
      <a href="sign-in.html" v-if="!signedIn">Sign In</a>
    </nav>
  `,

    methods: {
        signOut() {
            sessionStorage.removeItem('selectedProduct');
            sessionStorage.removeItem('items');
            sessionStorage.clear();
            this.$store.commit('signIn', null); // Clear customer state if needed
            window.location = '.'; // Redirect after signing out
        }
    }
};
