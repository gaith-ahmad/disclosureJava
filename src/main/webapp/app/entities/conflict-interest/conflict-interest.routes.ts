import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import ConflictInterestResolve from './route/conflict-interest-routing-resolve.service';

const conflictInterestRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/conflict-interest.component').then(m => m.ConflictInterestComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/conflict-interest-detail.component').then(m => m.ConflictInterestDetailComponent),
    resolve: {
      conflictInterest: ConflictInterestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/conflict-interest-update.component').then(m => m.ConflictInterestUpdateComponent),
    resolve: {
      conflictInterest: ConflictInterestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/conflict-interest-update.component').then(m => m.ConflictInterestUpdateComponent),
    resolve: {
      conflictInterest: ConflictInterestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default conflictInterestRoute;
