import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IConflictInterest, NewConflictInterest } from '../conflict-interest.model';

export type PartialUpdateConflictInterest = Partial<IConflictInterest> & Pick<IConflictInterest, 'id'>;

export type EntityResponseType = HttpResponse<IConflictInterest>;
export type EntityArrayResponseType = HttpResponse<IConflictInterest[]>;

@Injectable({ providedIn: 'root' })
export class ConflictInterestService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/conflict-interests');

  create(conflictInterest: NewConflictInterest): Observable<EntityResponseType> {
    return this.http.post<IConflictInterest>(this.resourceUrl, conflictInterest, { observe: 'response' });
  }

  update(conflictInterest: IConflictInterest): Observable<EntityResponseType> {
    return this.http.put<IConflictInterest>(
      `${this.resourceUrl}/${this.getConflictInterestIdentifier(conflictInterest)}`,
      conflictInterest,
      { observe: 'response' },
    );
  }

  partialUpdate(conflictInterest: PartialUpdateConflictInterest): Observable<EntityResponseType> {
    return this.http.patch<IConflictInterest>(
      `${this.resourceUrl}/${this.getConflictInterestIdentifier(conflictInterest)}`,
      conflictInterest,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConflictInterest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConflictInterest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getConflictInterestIdentifier(conflictInterest: Pick<IConflictInterest, 'id'>): number {
    return conflictInterest.id;
  }

  compareConflictInterest(o1: Pick<IConflictInterest, 'id'> | null, o2: Pick<IConflictInterest, 'id'> | null): boolean {
    return o1 && o2 ? this.getConflictInterestIdentifier(o1) === this.getConflictInterestIdentifier(o2) : o1 === o2;
  }

  addConflictInterestToCollectionIfMissing<Type extends Pick<IConflictInterest, 'id'>>(
    conflictInterestCollection: Type[],
    ...conflictInterestsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const conflictInterests: Type[] = conflictInterestsToCheck.filter(isPresent);
    if (conflictInterests.length > 0) {
      const conflictInterestCollectionIdentifiers = conflictInterestCollection.map(conflictInterestItem =>
        this.getConflictInterestIdentifier(conflictInterestItem),
      );
      const conflictInterestsToAdd = conflictInterests.filter(conflictInterestItem => {
        const conflictInterestIdentifier = this.getConflictInterestIdentifier(conflictInterestItem);
        if (conflictInterestCollectionIdentifiers.includes(conflictInterestIdentifier)) {
          return false;
        }
        conflictInterestCollectionIdentifiers.push(conflictInterestIdentifier);
        return true;
      });
      return [...conflictInterestsToAdd, ...conflictInterestCollection];
    }
    return conflictInterestCollection;
  }
}
