import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IGift } from '../gift.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../gift.test-samples';

import { GiftService } from './gift.service';

const requireRestSample: IGift = {
  ...sampleWithRequiredData,
};

describe('Gift Service', () => {
  let service: GiftService;
  let httpMock: HttpTestingController;
  let expectedResult: IGift | IGift[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(GiftService);
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

    it('should create a Gift', () => {
      const gift = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(gift).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Gift', () => {
      const gift = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(gift).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Gift', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Gift', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Gift', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addGiftToCollectionIfMissing', () => {
      it('should add a Gift to an empty array', () => {
        const gift: IGift = sampleWithRequiredData;
        expectedResult = service.addGiftToCollectionIfMissing([], gift);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gift);
      });

      it('should not add a Gift to an array that contains it', () => {
        const gift: IGift = sampleWithRequiredData;
        const giftCollection: IGift[] = [
          {
            ...gift,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, gift);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Gift to an array that doesn't contain it", () => {
        const gift: IGift = sampleWithRequiredData;
        const giftCollection: IGift[] = [sampleWithPartialData];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, gift);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gift);
      });

      it('should add only unique Gift to an array', () => {
        const giftArray: IGift[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const giftCollection: IGift[] = [sampleWithRequiredData];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, ...giftArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const gift: IGift = sampleWithRequiredData;
        const gift2: IGift = sampleWithPartialData;
        expectedResult = service.addGiftToCollectionIfMissing([], gift, gift2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gift);
        expect(expectedResult).toContain(gift2);
      });

      it('should accept null and undefined values', () => {
        const gift: IGift = sampleWithRequiredData;
        expectedResult = service.addGiftToCollectionIfMissing([], null, gift, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gift);
      });

      it('should return initial array if no Gift is added', () => {
        const giftCollection: IGift[] = [sampleWithRequiredData];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, undefined, null);
        expect(expectedResult).toEqual(giftCollection);
      });
    });

    describe('compareGift', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareGift(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 17243 };
        const entity2 = null;

        const compareResult1 = service.compareGift(entity1, entity2);
        const compareResult2 = service.compareGift(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 17243 };
        const entity2 = { id: 8361 };

        const compareResult1 = service.compareGift(entity1, entity2);
        const compareResult2 = service.compareGift(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 17243 };
        const entity2 = { id: 17243 };

        const compareResult1 = service.compareGift(entity1, entity2);
        const compareResult2 = service.compareGift(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
