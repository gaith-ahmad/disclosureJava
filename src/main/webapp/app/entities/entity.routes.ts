import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'disclosureApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'conflict-interest',
    data: { pageTitle: 'disclosureApp.conflictInterest.home.title' },
    loadChildren: () => import('./conflict-interest/conflict-interest.routes'),
  },
  {
    path: 'disclosure',
    data: { pageTitle: 'disclosureApp.disclosure.home.title' },
    loadChildren: () => import('./disclosure/disclosure.routes'),
  },
  {
    path: 'employee',
    data: { pageTitle: 'disclosureApp.employee.home.title' },
    loadChildren: () => import('./employee/employee.routes'),
  },
  {
    path: 'gift',
    data: { pageTitle: 'disclosureApp.gift.home.title' },
    loadChildren: () => import('./gift/gift.routes'),
  },
  {
    path: 'manager-info',
    data: { pageTitle: 'disclosureApp.managerInfo.home.title' },
    loadChildren: () => import('./manager-info/manager-info.routes'),
  },
  {
    path: 'relatives',
    data: { pageTitle: 'disclosureApp.relatives.home.title' },
    loadChildren: () => import('./relatives/relatives.routes'),
  },
  {
    path: 'user-info',
    data: { pageTitle: 'disclosureApp.userInfo.home.title' },
    loadChildren: () => import('./user-info/user-info.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
