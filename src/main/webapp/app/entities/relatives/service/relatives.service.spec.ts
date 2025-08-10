import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IRelatives } from '../relatives.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../relatives.test-samples';

import { RelativesService } from './relatives.service';

const requireRestSample: IRelatives = {
  ...sampleWithRequiredData,
};

describe('Relatives Service', () => {
  let service: RelativesService;
  let httpMock: HttpTestingController;
  let expectedResult: IRelatives | IRelatives[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(RelativesService);
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

    it('should create a Relatives', () => {
      const relatives = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(relatives).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Relatives', () => {
      const relatives = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(relatives).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Relatives', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Relatives', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Relatives', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRelativesToCollectionIfMissing', () => {
      it('should add a Relatives to an empty array', () => {
        const relatives: IRelatives = sampleWithRequiredData;
        expectedResult = service.addRelativesToCollectionIfMissing([], relatives);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(relatives);
      });

      it('should not add a Relatives to an array that contains it', () => {
        const relatives: IRelatives = sampleWithRequiredData;
        const relativesCollection: IRelatives[] = [
          {
            ...relatives,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRelativesToCollectionIfMissing(relativesCollection, relatives);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Relatives to an array that doesn't contain it", () => {
        const relatives: IRelatives = sampleWithRequiredData;
        const relativesCollection: IRelatives[] = [sampleWithPartialData];
        expectedResult = service.addRelativesToCollectionIfMissing(relativesCollection, relatives);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(relatives);
      });

      it('should add only unique Relatives to an array', () => {
        const relativesArray: IRelatives[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const relativesCollection: IRelatives[] = [sampleWithRequiredData];
        expectedResult = service.addRelativesToCollectionIfMissing(relativesCollection, ...relativesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const relatives: IRelatives = sampleWithRequiredData;
        const relatives2: IRelatives = sampleWithPartialData;
        expectedResult = service.addRelativesToCollectionIfMissing([], relatives, relatives2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(relatives);
        expect(expectedResult).toContain(relatives2);
      });

      it('should accept null and undefined values', () => {
        const relatives: IRelatives = sampleWithRequiredData;
        expectedResult = service.addRelativesToCollectionIfMissing([], null, relatives, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(relatives);
      });

      it('should return initial array if no Relatives is added', () => {
        const relativesCollection: IRelatives[] = [sampleWithRequiredData];
        expectedResult = service.addRelativesToCollectionIfMissing(relativesCollection, undefined, null);
        expect(expectedResult).toEqual(relativesCollection);
      });
    });

    describe('compareRelatives', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRelatives(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 22367 };
        const entity2 = null;

        const compareResult1 = service.compareRelatives(entity1, entity2);
        const compareResult2 = service.compareRelatives(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 22367 };
        const entity2 = { id: 23212 };

        const compareResult1 = service.compareRelatives(entity1, entity2);
        const compareResult2 = service.compareRelatives(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 22367 };
        const entity2 = { id: 22367 };

        const compareResult1 = service.compareRelatives(entity1, entity2);
        const compareResult2 = service.compareRelatives(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
