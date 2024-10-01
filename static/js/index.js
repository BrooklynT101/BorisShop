/* global Vue */

// import vuex store
import { sessionStore } from './session-store.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// create the Vue controller
const app = Vue.createApp();

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// sse the Vuex store
app.use(sessionStore);

// attach the controller to the <main> tag
app.mount("main");