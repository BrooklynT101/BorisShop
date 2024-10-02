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

        addItem(product, quantity) {
            //            error checking
            if (!product) {
                console.error('No product selected');
                return;
            }

////cloning the cart items to remove vue proxy and circular referencing
//            let cartItems = JSON.parse(JSON.stringify(this.items || [])); // retrieve current items or initialize empty array

            // checking if the product already exists in the cart
            const existingItem = this.items.find(item => item.productId === product.productId);
            // if it does, update quantity
            if (existingItem) {
                existingItem.quantity += parseInt(quantity, 10);
                // else just add it
            } else {
                // Add the new product to the cart
                this.items.push({
                    productId: product.productId,
                    name: product.name,
                    price: product.listPrice,
                    quantity: parseInt(quantity, 10)
                });
            }

            console.log('Cart Items:', this.items);
            console.log('Product to be added:', product);
            // commit clean cart to Vuex state
            sessionStore.commit('updateCart', this.items);
            console.log(`Added ${quantity} of ${product.name} to the cart.`);
//            window.location = 'view-products.html';
        },
        
        //update cart items
        updateCart(state, items) {
            state.items = items;
        }

    },
    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});