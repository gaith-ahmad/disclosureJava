import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRelatives } from '../relatives.model';
import { RelativesService } from '../service/relatives.service';

const relativesResolve = (route: ActivatedRouteSnapshot): Observable<null | IRelatives> => {
  const id = route.params.id;
  if (id) {
    return inject(RelativesService)
      .find(id)
      .pipe(
        mergeMap((relatives: HttpResponse<IRelatives>) => {
          if (relatives.body) {
            return of(relatives.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default relativesResolve;
