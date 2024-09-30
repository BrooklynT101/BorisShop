export const navigationMenu = {
    template:
            `
            <nav class="navbar">
                <ul class="nav-menu">
                    <li><a href="index.html">Home</a></li>
                    <li v-if="currCustomer"><a href="view-products.html">View Products</a></li>
                    <li v-if="!currCustomer"><a href="sign-in.html">Sign In</a></li>
                    <li v-if="!currCustomer"><a href="create-account.html">Create Account</a></li>
                    <li v-if="currCustomer"><a href="customer-account.html">My Account</a></li>
                    <li v-if="currCustomer"><a @click="logOut">Log Out</a></li>
                </ul>
            </nav>
		`,
    computed: {
        currCustomer() {
            return this.$store.state.currCustomer;
        }
    },
    methods: {
        logOut() {
            this.$store.commit('logOutCustomer');
            window.location = 'index.html'; // Redirect after logout
        }
    }
};