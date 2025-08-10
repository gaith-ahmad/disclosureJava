import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import RelativesResolve from './route/relatives-routing-resolve.service';

const relativesRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/relatives.component').then(m => m.RelativesComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/relatives-detail.component').then(m => m.RelativesDetailComponent),
    resolve: {
      relatives: RelativesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/relatives-update.component').then(m => m.RelativesUpdateComponent),
    resolve: {
      relatives: RelativesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/relatives-update.component').then(m => m.RelativesUpdateComponent),
    resolve: {
      relatives: RelativesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default relativesRoute;
