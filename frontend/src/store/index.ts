import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  strict: false, // process.env.NODE_ENV !== 'production',
  plugins: [createPersistedState()],
  state: {
    isSidebarMinimized: false,
    userName: "Vasili S",
    cctvList: []
  },
  mutations: {
    updateSidebarCollapsedState(state, isSidebarMinimized) {
      state.isSidebarMinimized = isSidebarMinimized;
    },
    changeUserName(state, newUserName) {
      state.userName = newUserName;
    }
  },
  getters: {
    getList: function(state) {
      return state.cctvList;
    }
  }
});
