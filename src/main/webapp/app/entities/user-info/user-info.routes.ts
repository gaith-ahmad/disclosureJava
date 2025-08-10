import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import UserInfoResolve from './route/user-info-routing-resolve.service';

const userInfoRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/user-info.component').then(m => m.UserInfoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/user-info-detail.component').then(m => m.UserInfoDetailComponent),
    resolve: {
      userInfo: UserInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/user-info-update.component').then(m => m.UserInfoUpdateComponent),
    resolve: {
      userInfo: UserInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/user-info-update.component').then(m => m.UserInfoUpdateComponent),
    resolve: {
      userInfo: UserInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default userInfoRoute;
