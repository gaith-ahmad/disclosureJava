import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IDisclosure } from '../disclosure.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../disclosure.test-samples';

import { DisclosureService } from './disclosure.service';

const requireRestSample: IDisclosure = {
  ...sampleWithRequiredData,
};

describe('Disclosure Service', () => {
  let service: DisclosureService;
  let httpMock: HttpTestingController;
  let expectedResult: IDisclosure | IDisclosure[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(DisclosureService);
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

    it('should create a Disclosure', () => {
      const disclosure = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(disclosure).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Disclosure', () => {
      const disclosure = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(disclosure).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Disclosure', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Disclosure', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Disclosure', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDisclosureToCollectionIfMissing', () => {
      it('should add a Disclosure to an empty array', () => {
        const disclosure: IDisclosure = sampleWithRequiredData;
        expectedResult = service.addDisclosureToCollectionIfMissing([], disclosure);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(disclosure);
      });

      it('should not add a Disclosure to an array that contains it', () => {
        const disclosure: IDisclosure = sampleWithRequiredData;
        const disclosureCollection: IDisclosure[] = [
          {
            ...disclosure,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDisclosureToCollectionIfMissing(disclosureCollection, disclosure);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Disclosure to an array that doesn't contain it", () => {
        const disclosure: IDisclosure = sampleWithRequiredData;
        const disclosureCollection: IDisclosure[] = [sampleWithPartialData];
        expectedResult = service.addDisclosureToCollectionIfMissing(disclosureCollection, disclosure);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(disclosure);
      });

      it('should add only unique Disclosure to an array', () => {
        const disclosureArray: IDisclosure[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const disclosureCollection: IDisclosure[] = [sampleWithRequiredData];
        expectedResult = service.addDisclosureToCollectionIfMissing(disclosureCollection, ...disclosureArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const disclosure: IDisclosure = sampleWithRequiredData;
        const disclosure2: IDisclosure = sampleWithPartialData;
        expectedResult = service.addDisclosureToCollectionIfMissing([], disclosure, disclosure2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(disclosure);
        expect(expectedResult).toContain(disclosure2);
      });

      it('should accept null and undefined values', () => {
        const disclosure: IDisclosure = sampleWithRequiredData;
        expectedResult = service.addDisclosureToCollectionIfMissing([], null, disclosure, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(disclosure);
      });

      it('should return initial array if no Disclosure is added', () => {
        const disclosureCollection: IDisclosure[] = [sampleWithRequiredData];
        expectedResult = service.addDisclosureToCollectionIfMissing(disclosureCollection, undefined, null);
        expect(expectedResult).toEqual(disclosureCollection);
      });
    });

    describe('compareDisclosure', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDisclosure(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 10911 };
        const entity2 = null;

        const compareResult1 = service.compareDisclosure(entity1, entity2);
        const compareResult2 = service.compareDisclosure(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 10911 };
        const entity2 = { id: 26183 };

        const compareResult1 = service.compareDisclosure(entity1, entity2);
        const compareResult2 = service.compareDisclosure(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 10911 };
        const entity2 = { id: 10911 };

        const compareResult1 = service.compareDisclosure(entity1, entity2);
        const compareResult2 = service.compareDisclosure(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
