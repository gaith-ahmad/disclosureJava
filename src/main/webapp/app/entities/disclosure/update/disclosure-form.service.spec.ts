import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../disclosure.test-samples';

import { DisclosureFormService } from './disclosure-form.service';

describe('Disclosure Form Service', () => {
  let service: DisclosureFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisclosureFormService);
  });

  describe('Service methods', () => {
    describe('createDisclosureFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDisclosureFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            purposeOfDisclosure: expect.any(Object),
            creationDate: expect.any(Object),
            name: expect.any(Object),
            jobTitle: expect.any(Object),
            jobNumber: expect.any(Object),
            ext: expect.any(Object),
            publicAdministration: expect.any(Object),
            administration: expect.any(Object),
            confirm: expect.any(Object),
            email: expect.any(Object),
            emailDirectManager: expect.any(Object),
            nameDirectManager: expect.any(Object),
            jobNumberDirectManager: expect.any(Object),
            jobTitleDirectManager: expect.any(Object),
            extDirectManager: expect.any(Object),
            areThereRelatives: expect.any(Object),
            file: expect.any(Object),
            filename: expect.any(Object),
            gift: expect.any(Object),
            conflictInterest: expect.any(Object),
          }),
        );
      });

      it('passing IDisclosure should create a new form with FormGroup', () => {
        const formGroup = service.createDisclosureFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            purposeOfDisclosure: expect.any(Object),
            creationDate: expect.any(Object),
            name: expect.any(Object),
            jobTitle: expect.any(Object),
            jobNumber: expect.any(Object),
            ext: expect.any(Object),
            publicAdministration: expect.any(Object),
            administration: expect.any(Object),
            confirm: expect.any(Object),
            email: expect.any(Object),
            emailDirectManager: expect.any(Object),
            nameDirectManager: expect.any(Object),
            jobNumberDirectManager: expect.any(Object),
            jobTitleDirectManager: expect.any(Object),
            extDirectManager: expect.any(Object),
            areThereRelatives: expect.any(Object),
            file: expect.any(Object),
            filename: expect.any(Object),
            gift: expect.any(Object),
            conflictInterest: expect.any(Object),
          }),
        );
      });
    });

    describe('getDisclosure', () => {
      it('should return NewDisclosure for default Disclosure initial value', () => {
        const formGroup = service.createDisclosureFormGroup(sampleWithNewData);

        const disclosure = service.getDisclosure(formGroup) as any;

        expect(disclosure).toMatchObject(sampleWithNewData);
      });

      it('should return NewDisclosure for empty Disclosure initial value', () => {
        const formGroup = service.createDisclosureFormGroup();

        const disclosure = service.getDisclosure(formGroup) as any;

        expect(disclosure).toMatchObject({});
      });

      it('should return IDisclosure', () => {
        const formGroup = service.createDisclosureFormGroup(sampleWithRequiredData);

        const disclosure = service.getDisclosure(formGroup) as any;

        expect(disclosure).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDisclosure should not enable id FormControl', () => {
        const formGroup = service.createDisclosureFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDisclosure should disable id FormControl', () => {
        const formGroup = service.createDisclosureFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
