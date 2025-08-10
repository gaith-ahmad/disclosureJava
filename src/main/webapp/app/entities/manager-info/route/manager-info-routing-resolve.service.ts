import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IManagerInfo } from '../manager-info.model';
import { ManagerInfoService } from '../service/manager-info.service';

const managerInfoResolve = (route: ActivatedRouteSnapshot): Observable<null | IManagerInfo> => {
  const id = route.params.id;
  if (id) {
    return inject(ManagerInfoService)
      .find(id)
      .pipe(
        mergeMap((managerInfo: HttpResponse<IManagerInfo>) => {
          if (managerInfo.body) {
            return of(managerInfo.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default managerInfoResolve;
