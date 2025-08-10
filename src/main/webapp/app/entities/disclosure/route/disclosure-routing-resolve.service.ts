import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDisclosure } from '../disclosure.model';
import { DisclosureService } from '../service/disclosure.service';

const disclosureResolve = (route: ActivatedRouteSnapshot): Observable<null | IDisclosure> => {
  const id = route.params.id;
  if (id) {
    return inject(DisclosureService)
      .find(id)
      .pipe(
        mergeMap((disclosure: HttpResponse<IDisclosure>) => {
          if (disclosure.body) {
            return of(disclosure.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default disclosureResolve;
