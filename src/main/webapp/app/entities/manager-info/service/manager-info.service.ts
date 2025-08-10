import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IManagerInfo, NewManagerInfo } from '../manager-info.model';

export type PartialUpdateManagerInfo = Partial<IManagerInfo> & Pick<IManagerInfo, 'id'>;

export type EntityResponseType = HttpResponse<IManagerInfo>;
export type EntityArrayResponseType = HttpResponse<IManagerInfo[]>;

@Injectable({ providedIn: 'root' })
export class ManagerInfoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/manager-infos');

  create(managerInfo: NewManagerInfo): Observable<EntityResponseType> {
    return this.http.post<IManagerInfo>(this.resourceUrl, managerInfo, { observe: 'response' });
  }

  update(managerInfo: IManagerInfo): Observable<EntityResponseType> {
    return this.http.put<IManagerInfo>(`${this.resourceUrl}/${this.getManagerInfoIdentifier(managerInfo)}`, managerInfo, {
      observe: 'response',
    });
  }

  partialUpdate(managerInfo: PartialUpdateManagerInfo): Observable<EntityResponseType> {
    return this.http.patch<IManagerInfo>(`${this.resourceUrl}/${this.getManagerInfoIdentifier(managerInfo)}`, managerInfo, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IManagerInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IManagerInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getManagerInfoIdentifier(managerInfo: Pick<IManagerInfo, 'id'>): number {
    return managerInfo.id;
  }

  compareManagerInfo(o1: Pick<IManagerInfo, 'id'> | null, o2: Pick<IManagerInfo, 'id'> | null): boolean {
    return o1 && o2 ? this.getManagerInfoIdentifier(o1) === this.getManagerInfoIdentifier(o2) : o1 === o2;
  }

  addManagerInfoToCollectionIfMissing<Type extends Pick<IManagerInfo, 'id'>>(
    managerInfoCollection: Type[],
    ...managerInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const managerInfos: Type[] = managerInfosToCheck.filter(isPresent);
    if (managerInfos.length > 0) {
      const managerInfoCollectionIdentifiers = managerInfoCollection.map(managerInfoItem => this.getManagerInfoIdentifier(managerInfoItem));
      const managerInfosToAdd = managerInfos.filter(managerInfoItem => {
        const managerInfoIdentifier = this.getManagerInfoIdentifier(managerInfoItem);
        if (managerInfoCollectionIdentifiers.includes(managerInfoIdentifier)) {
          return false;
        }
        managerInfoCollectionIdentifiers.push(managerInfoIdentifier);
        return true;
      });
      return [...managerInfosToAdd, ...managerInfoCollection];
    }
    return managerInfoCollection;
  }
}
