import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IConflictInterest } from '../conflict-interest.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../conflict-interest.test-samples';

import { ConflictInterestService } from './conflict-interest.service';

const requireRestSample: IConflictInterest = {
  ...sampleWithRequiredData,
};

describe('ConflictInterest Service', () => {
  let service: ConflictInterestService;
  let httpMock: HttpTestingController;
  let expectedResult: IConflictInterest | IConflictInterest[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ConflictInterestService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ConflictInterest', () => {
      const conflictInterest = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(conflictInterest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ConflictInterest', () => {
      const conflictInterest = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(conflictInterest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ConflictInterest', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ConflictInterest', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ConflictInterest', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addConflictInterestToCollectionIfMissing', () => {
      it('should add a ConflictInterest to an empty array', () => {
        const conflictInterest: IConflictInterest = sampleWithRequiredData;
        expectedResult = service.addConflictInterestToCollectionIfMissing([], conflictInterest);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(conflictInterest);
      });

      it('should not add a ConflictInterest to an array that contains it', () => {
        const conflictInterest: IConflictInterest = sampleWithRequiredData;
        const conflictInterestCollection: IConflictInterest[] = [
          {
            ...conflictInterest,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addConflictInterestToCollectionIfMissing(conflictInterestCollection, conflictInterest);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ConflictInterest to an array that doesn't contain it", () => {
        const conflictInterest: IConflictInterest = sampleWithRequiredData;
        const conflictInterestCollection: IConflictInterest[] = [sampleWithPartialData];
        expectedResult = service.addConflictInterestToCollectionIfMissing(conflictInterestCollection, conflictInterest);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(conflictInterest);
      });

      it('should add only unique ConflictInterest to an array', () => {
        const conflictInterestArray: IConflictInterest[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const conflictInterestCollection: IConflictInterest[] = [sampleWithRequiredData];
        expectedResult = service.addConflictInterestToCollectionIfMissing(conflictInterestCollection, ...conflictInterestArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const conflictInterest: IConflictInterest = sampleWithRequiredData;
        const conflictInterest2: IConflictInterest = sampleWithPartialData;
        expectedResult = service.addConflictInterestToCollectionIfMissing([], conflictInterest, conflictInterest2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(conflictInterest);
        expect(expectedResult).toContain(conflictInterest2);
      });

      it('should accept null and undefined values', () => {
        const conflictInterest: IConflictInterest = sampleWithRequiredData;
        expectedResult = service.addConflictInterestToCollectionIfMissing([], null, conflictInterest, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(conflictInterest);
      });

      it('should return initial array if no ConflictInterest is added', () => {
        const conflictInterestCollection: IConflictInterest[] = [sampleWithRequiredData];
        expectedResult = service.addConflictInterestToCollectionIfMissing(conflictInterestCollection, undefined, null);
        expect(expectedResult).toEqual(conflictInterestCollection);
      });
    });

    describe('compareConflictInterest', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareConflictInterest(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 26216 };
        const entity2 = null;

        const compareResult1 = service.compareConflictInterest(entity1, entity2);
        const compareResult2 = service.compareConflictInterest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 26216 };
        const entity2 = { id: 16902 };

        const compareResult1 = service.compareConflictInterest(entity1, entity2);
        const compareResult2 = service.compareConflictInterest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 26216 };
        const entity2 = { id: 26216 };

        const compareResult1 = service.compareConflictInterest(entity1, entity2);
        const compareResult2 = service.compareConflictInterest(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
