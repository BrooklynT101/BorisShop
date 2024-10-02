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
//            state.items = new Array();
        },
        
        // user selects a product
        selectProduct(state, product) {
            state.selectedProduct = product;
        },
        
        addItem(state, saleItem) {
            // error checking
            if (!saleItem || !saleItem.product) {
                console.error('Sale item or Product is invalid');
                return;
            }

            console.log('Product to be added:', saleItem.product);
            // cloning the cart items to remove vue proxy and circular referencing
            // let cartItems = JSON.parse(JSON.stringify(this.items || [])); // retrieve current items or initialize empty array

            // checking if the product already exists in the cart
            const existingItem = state.items.find(item => item.product.productId === saleItem.product.productId);
            if (existingItem) {
                console.log('Found exisiting item in cart, updating quantity Purchased');
                // if it does, update quantityPurchased
                existingItem.quantityPurchased += saleItem.quantityPurchased;
            } else {
                console.log('Adding new item to cart');
                // else just add a new saleitem to the cart
                state.items.push({
                    product: saleItem.product,
                    quantityPurchased: saleItem.quantityPurchased,
                    salePrice: saleItem.salePrice
                });
            }

            console.log(`Added ${saleItem.quantityPurchased} of ${saleItem.product.name} to the cart.`);
        },
        //CGPT gave me the idea for the filtering by productId rather than product
        removeItem(state, productId) {
            let filteredItems = state.items.filter(item => item.product.productId !== productId);
            sessionStore.commit('updateCart', filteredItems);
        },

        //update cart items
        updateCart(state, items) {
            state.items = items;
        }

    },
    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});