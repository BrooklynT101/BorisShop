/* global Vue */

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// create the Vue controller
const app = Vue.createApp({
    computed: {
        ...Vuex.mapState({
                customer: 'customer'
        })
    },
//    had CGPT help me dynamically update the browser icon
    mounted() {
        const favicon = document.getElementById("favicon");
        if (this.customer) {
            //if signed in cats awake
            favicon.href = 'images/awake-cat.png';
        } else {
            //else it sleeps, hooonk shoooo
            favicon.href = 'images/sleeping-cat.png';
        }
    }
});

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);
    
// use the Vuex store
app.use(sessionStore);

// attach the controller to the <main> tag
app.mount("main");
