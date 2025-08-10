import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../manager-info.test-samples';

import { ManagerInfoFormService } from './manager-info-form.service';

describe('ManagerInfo Form Service', () => {
  let service: ManagerInfoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManagerInfoFormService);
  });

  describe('Service methods', () => {
    describe('createManagerInfoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createManagerInfoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            emailDirectManager: expect.any(Object),
            nameDirectManager: expect.any(Object),
            jobNumberDirectManager: expect.any(Object),
            jobTitleDirectManager: expect.any(Object),
            extDirectManager: expect.any(Object),
          }),
        );
      });

      it('passing IManagerInfo should create a new form with FormGroup', () => {
        const formGroup = service.createManagerInfoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            emailDirectManager: expect.any(Object),
            nameDirectManager: expect.any(Object),
            jobNumberDirectManager: expect.any(Object),
            jobTitleDirectManager: expect.any(Object),
            extDirectManager: expect.any(Object),
          }),
        );
      });
    });

    describe('getManagerInfo', () => {
      it('should return NewManagerInfo for default ManagerInfo initial value', () => {
        const formGroup = service.createManagerInfoFormGroup(sampleWithNewData);

        const managerInfo = service.getManagerInfo(formGroup) as any;

        expect(managerInfo).toMatchObject(sampleWithNewData);
      });

      it('should return NewManagerInfo for empty ManagerInfo initial value', () => {
        const formGroup = service.createManagerInfoFormGroup();

        const managerInfo = service.getManagerInfo(formGroup) as any;

        expect(managerInfo).toMatchObject({});
      });

      it('should return IManagerInfo', () => {
        const formGroup = service.createManagerInfoFormGroup(sampleWithRequiredData);

        const managerInfo = service.getManagerInfo(formGroup) as any;

        expect(managerInfo).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IManagerInfo should not enable id FormControl', () => {
        const formGroup = service.createManagerInfoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewManagerInfo should disable id FormControl', () => {
        const formGroup = service.createManagerInfoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
