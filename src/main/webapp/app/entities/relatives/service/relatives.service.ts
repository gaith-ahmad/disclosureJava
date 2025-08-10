import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRelatives, NewRelatives } from '../relatives.model';

export type PartialUpdateRelatives = Partial<IRelatives> & Pick<IRelatives, 'id'>;

export type EntityResponseType = HttpResponse<IRelatives>;
export type EntityArrayResponseType = HttpResponse<IRelatives[]>;

@Injectable({ providedIn: 'root' })
export class RelativesService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/relatives');

  create(relatives: NewRelatives): Observable<EntityResponseType> {
    return this.http.post<IRelatives>(this.resourceUrl, relatives, { observe: 'response' });
  }

  update(relatives: IRelatives): Observable<EntityResponseType> {
    return this.http.put<IRelatives>(`${this.resourceUrl}/${this.getRelativesIdentifier(relatives)}`, relatives, { observe: 'response' });
  }

  partialUpdate(relatives: PartialUpdateRelatives): Observable<EntityResponseType> {
    return this.http.patch<IRelatives>(`${this.resourceUrl}/${this.getRelativesIdentifier(relatives)}`, relatives, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatives>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatives[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRelativesIdentifier(relatives: Pick<IRelatives, 'id'>): number {
    return relatives.id;
  }

  compareRelatives(o1: Pick<IRelatives, 'id'> | null, o2: Pick<IRelatives, 'id'> | null): boolean {
    return o1 && o2 ? this.getRelativesIdentifier(o1) === this.getRelativesIdentifier(o2) : o1 === o2;
  }

  addRelativesToCollectionIfMissing<Type extends Pick<IRelatives, 'id'>>(
    relativesCollection: Type[],
    ...relativesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const relatives: Type[] = relativesToCheck.filter(isPresent);
    if (relatives.length > 0) {
      const relativesCollectionIdentifiers = relativesCollection.map(relativesItem => this.getRelativesIdentifier(relativesItem));
      const relativesToAdd = relatives.filter(relativesItem => {
        const relativesIdentifier = this.getRelativesIdentifier(relativesItem);
        if (relativesCollectionIdentifiers.includes(relativesIdentifier)) {
          return false;
        }
        relativesCollectionIdentifiers.push(relativesIdentifier);
        return true;
      });
      return [...relativesToAdd, ...relativesCollection];
    }
    return relativesCollection;
  }
}
