export default {
  root: {
    name: "/user",
    displayName: "navigationRoutes.home",
  },
  routes: [
    {
      name: "userboard",
      displayName: "menu.dashboard",
      meta: {
        icon: "vuestic-iconset-dashboard",
      },
    },
    // {
    //   name: "usertables",
    //   displayName: "menu.tables",
    //   meta: {
    //     icon: "vuestic-iconset-tables"
    //   }
    // },
    {
      name: "usercctv",
      displayName: "CCTV",
      meta: {
        icon: "vuestic-iconset-video",
      },
    },
    {
      name: "usercalendar",
      displayName: "menu.calendar",
      meta: {
        icon: "vuestic-iconset-forms",
      },
    },
  ],
};
