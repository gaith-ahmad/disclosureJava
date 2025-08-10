import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IConflictInterest } from '../conflict-interest.model';
import { ConflictInterestService } from '../service/conflict-interest.service';

const conflictInterestResolve = (route: ActivatedRouteSnapshot): Observable<null | IConflictInterest> => {
  const id = route.params.id;
  if (id) {
    return inject(ConflictInterestService)
      .find(id)
      .pipe(
        mergeMap((conflictInterest: HttpResponse<IConflictInterest>) => {
          if (conflictInterest.body) {
            return of(conflictInterest.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default conflictInterestResolve;
