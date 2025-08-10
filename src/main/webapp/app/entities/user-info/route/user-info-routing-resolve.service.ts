import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserInfo } from '../user-info.model';
import { UserInfoService } from '../service/user-info.service';

const userInfoResolve = (route: ActivatedRouteSnapshot): Observable<null | IUserInfo> => {
  const id = route.params.id;
  if (id) {
    return inject(UserInfoService)
      .find(id)
      .pipe(
        mergeMap((userInfo: HttpResponse<IUserInfo>) => {
          if (userInfo.body) {
            return of(userInfo.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default userInfoResolve;
