//import { createApp } from 'vue';  // Import Vue from node_modules
//
//// import the navigation menu
////import { navigationMenu } from './navigation-menu.js';
//
//const navigationMenu = {
//    template:
//            `
//            <nav class="navbar">
//                <ul class="nav-menu">
//                    <li><a href="index.html" class="nav-link">Home</a></li>
//                    <li><a href="view-products.html" class="nav-link">View Products</a></li>
//                    <li><a href="sign-in.html" class="nav-link">Sign In</a></li>
//                    <li><a href="create-account.html" class="nav-link">Create an Account</a></li>
//                </ul>
//            </nav>
//		`
//};
//
//// create the Vue app
//const app = createApp({});
//
//// register the navigation menu under the <navmenu> tag
//app.component('navmenu', navigationMenu);
//
//// attach the controller to the <main> tag
//app.mount("main

///* global Vue */

// create the Vue controller
const app = Vue.createApp();

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// attach the controller to the <main> tag
app.mount("main");