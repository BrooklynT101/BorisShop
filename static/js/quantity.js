/* global Vue, sessionStorage */

// import vuex store
import { sessionStore } from './session-store.js';

// retrieve the selected product from sessionStorage
const selectedProduct = JSON.parse(sessionStorage.getItem("selectedProduct"));

const app = Vue.createApp({
    data() {
        return {
            product: selectedProduct,
            quantity: 1 // Default quantity is set to 1
        };
    },
    methods: {
        addToCart() {
            const cartItems = JSON.parse(sessionStorage.getItem("items")) || []; // Retrieve current cart or initialize an empty array

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
    console.log(`Added `,this.quantity,` of Product Name to the cart.`);
            }

            // Save the updated cart back to sessionStorage
            sessionStorage.setItem("items", JSON.stringify(cartItems));

    // Redirect to cart page after adding item
            window.location = 'view-products.html';
        }
    }
});

// mount the Vue instance
app.mount("main");


function addToCart() {
    const quantity = document.getElementById('quantity').value;
    // Logic to add item to cart
    console.log(`Added ${quantity} of Product Name to the cart.`);
    // Redirect to cart page after adding item
    window.location = 'view-products.html';
}