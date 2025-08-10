import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IGift } from '../gift.model';
import { GiftService } from '../service/gift.service';

const giftResolve = (route: ActivatedRouteSnapshot): Observable<null | IGift> => {
  const id = route.params.id;
  if (id) {
    return inject(GiftService)
      .find(id)
      .pipe(
        mergeMap((gift: HttpResponse<IGift>) => {
          if (gift.body) {
            return of(gift.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default giftResolve;
