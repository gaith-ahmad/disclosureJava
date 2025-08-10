import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import ManagerInfoResolve from './route/manager-info-routing-resolve.service';

const managerInfoRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/manager-info.component').then(m => m.ManagerInfoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/manager-info-detail.component').then(m => m.ManagerInfoDetailComponent),
    resolve: {
      managerInfo: ManagerInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/manager-info-update.component').then(m => m.ManagerInfoUpdateComponent),
    resolve: {
      managerInfo: ManagerInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/manager-info-update.component').then(m => m.ManagerInfoUpdateComponent),
    resolve: {
      managerInfo: ManagerInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default managerInfoRoute;
