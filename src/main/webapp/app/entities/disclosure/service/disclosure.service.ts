import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDisclosure, NewDisclosure } from '../disclosure.model';

export type PartialUpdateDisclosure = Partial<IDisclosure> & Pick<IDisclosure, 'id'>;

export type EntityResponseType = HttpResponse<IDisclosure>;
export type EntityArrayResponseType = HttpResponse<IDisclosure[]>;

@Injectable({ providedIn: 'root' })
export class DisclosureService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/disclosures');

  create(disclosure: NewDisclosure): Observable<EntityResponseType> {
    return this.http.post<IDisclosure>(this.resourceUrl, disclosure, { observe: 'response' });
  }

  update(disclosure: IDisclosure): Observable<EntityResponseType> {
    return this.http.put<IDisclosure>(`${this.resourceUrl}/${this.getDisclosureIdentifier(disclosure)}`, disclosure, {
      observe: 'response',
    });
  }

  partialUpdate(disclosure: PartialUpdateDisclosure): Observable<EntityResponseType> {
    return this.http.patch<IDisclosure>(`${this.resourceUrl}/${this.getDisclosureIdentifier(disclosure)}`, disclosure, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDisclosure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDisclosure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDisclosureIdentifier(disclosure: Pick<IDisclosure, 'id'>): number {
    return disclosure.id;
  }

  compareDisclosure(o1: Pick<IDisclosure, 'id'> | null, o2: Pick<IDisclosure, 'id'> | null): boolean {
    return o1 && o2 ? this.getDisclosureIdentifier(o1) === this.getDisclosureIdentifier(o2) : o1 === o2;
  }

  addDisclosureToCollectionIfMissing<Type extends Pick<IDisclosure, 'id'>>(
    disclosureCollection: Type[],
    ...disclosuresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const disclosures: Type[] = disclosuresToCheck.filter(isPresent);
    if (disclosures.length > 0) {
      const disclosureCollectionIdentifiers = disclosureCollection.map(disclosureItem => this.getDisclosureIdentifier(disclosureItem));
      const disclosuresToAdd = disclosures.filter(disclosureItem => {
        const disclosureIdentifier = this.getDisclosureIdentifier(disclosureItem);
        if (disclosureCollectionIdentifiers.includes(disclosureIdentifier)) {
          return false;
        }
        disclosureCollectionIdentifiers.push(disclosureIdentifier);
        return true;
      });
      return [...disclosuresToAdd, ...disclosureCollection];
    }
    return disclosureCollection;
  }
}
