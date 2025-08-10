import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IManagerInfo } from '../manager-info.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../manager-info.test-samples';

import { ManagerInfoService } from './manager-info.service';

const requireRestSample: IManagerInfo = {
  ...sampleWithRequiredData,
};

describe('ManagerInfo Service', () => {
  let service: ManagerInfoService;
  let httpMock: HttpTestingController;
  let expectedResult: IManagerInfo | IManagerInfo[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ManagerInfoService);
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

    it('should create a ManagerInfo', () => {
      const managerInfo = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(managerInfo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ManagerInfo', () => {
      const managerInfo = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(managerInfo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ManagerInfo', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ManagerInfo', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ManagerInfo', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addManagerInfoToCollectionIfMissing', () => {
      it('should add a ManagerInfo to an empty array', () => {
        const managerInfo: IManagerInfo = sampleWithRequiredData;
        expectedResult = service.addManagerInfoToCollectionIfMissing([], managerInfo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(managerInfo);
      });

      it('should not add a ManagerInfo to an array that contains it', () => {
        const managerInfo: IManagerInfo = sampleWithRequiredData;
        const managerInfoCollection: IManagerInfo[] = [
          {
            ...managerInfo,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addManagerInfoToCollectionIfMissing(managerInfoCollection, managerInfo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ManagerInfo to an array that doesn't contain it", () => {
        const managerInfo: IManagerInfo = sampleWithRequiredData;
        const managerInfoCollection: IManagerInfo[] = [sampleWithPartialData];
        expectedResult = service.addManagerInfoToCollectionIfMissing(managerInfoCollection, managerInfo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(managerInfo);
      });

      it('should add only unique ManagerInfo to an array', () => {
        const managerInfoArray: IManagerInfo[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const managerInfoCollection: IManagerInfo[] = [sampleWithRequiredData];
        expectedResult = service.addManagerInfoToCollectionIfMissing(managerInfoCollection, ...managerInfoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const managerInfo: IManagerInfo = sampleWithRequiredData;
        const managerInfo2: IManagerInfo = sampleWithPartialData;
        expectedResult = service.addManagerInfoToCollectionIfMissing([], managerInfo, managerInfo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(managerInfo);
        expect(expectedResult).toContain(managerInfo2);
      });

      it('should accept null and undefined values', () => {
        const managerInfo: IManagerInfo = sampleWithRequiredData;
        expectedResult = service.addManagerInfoToCollectionIfMissing([], null, managerInfo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(managerInfo);
      });

      it('should return initial array if no ManagerInfo is added', () => {
        const managerInfoCollection: IManagerInfo[] = [sampleWithRequiredData];
        expectedResult = service.addManagerInfoToCollectionIfMissing(managerInfoCollection, undefined, null);
        expect(expectedResult).toEqual(managerInfoCollection);
      });
    });

    describe('compareManagerInfo', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareManagerInfo(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 26966 };
        const entity2 = null;

        const compareResult1 = service.compareManagerInfo(entity1, entity2);
        const compareResult2 = service.compareManagerInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 26966 };
        const entity2 = { id: 23884 };

        const compareResult1 = service.compareManagerInfo(entity1, entity2);
        const compareResult2 = service.compareManagerInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 26966 };
        const entity2 = { id: 26966 };

        const compareResult1 = service.compareManagerInfo(entity1, entity2);
        const compareResult2 = service.compareManagerInfo(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
