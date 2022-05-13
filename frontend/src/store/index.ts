import { createStore } from 'vuex'
import jwt_decode from "jwt-decode"
import createPersistedState from "vuex-persistedstate";


// var token =
// var decoded = jwt_decode(token);


export default createStore({
  plugins: [createPersistedState()],
  strict: true, // process.env.NODE_ENV !== 'production',
  state: {
    isSidebarMinimized: false,
    // userName: 'Vasili S',
    userName: '',
    // userToken: '',
    // userName: jwt_decode(this.store.state.userToken),

  },
  mutations: {
    updateSidebarCollapsedState(state, isSidebarMinimized) {
      state.isSidebarMinimized = isSidebarMinimized
    },
    changeUserName(state, newUserName) {
      state.userName = newUserName
    }
  },
})
