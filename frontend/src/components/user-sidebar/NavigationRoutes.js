export default {
  root: {
    name: '/',
    displayName: 'navigationRoutes.home',
  },
  routes: [
    {
      name: 'dashboard',
      displayName: 'menu.dashboard',
      meta: {
        icon: 'vuestic-iconset-dashboard',
      },
    },
    {
      name: 'tables',
      displayName: 'menu.tables',
      meta: {
        icon: 'vuestic-iconset-tables',
      },
      children: [
        {
          name: 'markup',
          displayName: 'menu.markupTables',
        },
      ],
    },
    {
      name: 'cctvbase',
      displayName: 'CCTV',
      meta: {
        icon: 'vuestic-iconset-video',
      },
      disabled: true,
      children: [
        {
          name: 'cctvbase',
          displayName: 'base',
        },

      ],
    },
  ],
}
