import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGift, NewGift } from '../gift.model';

export type PartialUpdateGift = Partial<IGift> & Pick<IGift, 'id'>;

export type EntityResponseType = HttpResponse<IGift>;
export type EntityArrayResponseType = HttpResponse<IGift[]>;

@Injectable({ providedIn: 'root' })
export class GiftService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/gifts');

  create(gift: NewGift): Observable<EntityResponseType> {
    return this.http.post<IGift>(this.resourceUrl, gift, { observe: 'response' });
  }

  update(gift: IGift): Observable<EntityResponseType> {
    return this.http.put<IGift>(`${this.resourceUrl}/${this.getGiftIdentifier(gift)}`, gift, { observe: 'response' });
  }

  partialUpdate(gift: PartialUpdateGift): Observable<EntityResponseType> {
    return this.http.patch<IGift>(`${this.resourceUrl}/${this.getGiftIdentifier(gift)}`, gift, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGift>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGift[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getGiftIdentifier(gift: Pick<IGift, 'id'>): number {
    return gift.id;
  }

  compareGift(o1: Pick<IGift, 'id'> | null, o2: Pick<IGift, 'id'> | null): boolean {
    return o1 && o2 ? this.getGiftIdentifier(o1) === this.getGiftIdentifier(o2) : o1 === o2;
  }

  addGiftToCollectionIfMissing<Type extends Pick<IGift, 'id'>>(
    giftCollection: Type[],
    ...giftsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const gifts: Type[] = giftsToCheck.filter(isPresent);
    if (gifts.length > 0) {
      const giftCollectionIdentifiers = giftCollection.map(giftItem => this.getGiftIdentifier(giftItem));
      const giftsToAdd = gifts.filter(giftItem => {
        const giftIdentifier = this.getGiftIdentifier(giftItem);
        if (giftCollectionIdentifiers.includes(giftIdentifier)) {
          return false;
        }
        giftCollectionIdentifiers.push(giftIdentifier);
        return true;
      });
      return [...giftsToAdd, ...giftCollection];
    }
    return giftCollection;
  }
}
