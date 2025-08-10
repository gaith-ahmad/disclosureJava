import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../relatives.test-samples';

import { RelativesFormService } from './relatives-form.service';

describe('Relatives Form Service', () => {
  let service: RelativesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RelativesFormService);
  });

  describe('Service methods', () => {
    describe('createRelativesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRelativesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nameDiscloser: expect.any(Object),
            nameRelative: expect.any(Object),
            jobTitleRelative: expect.any(Object),
            relativeJobNumber: expect.any(Object),
            emailRelative: expect.any(Object),
            relativeExtensionNumber: expect.any(Object),
            relativeRelationship: expect.any(Object),
            generalAdministrationRelative: expect.any(Object),
            administrationRelative: expect.any(Object),
            disclosure: expect.any(Object),
          }),
        );
      });

      it('passing IRelatives should create a new form with FormGroup', () => {
        const formGroup = service.createRelativesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nameDiscloser: expect.any(Object),
            nameRelative: expect.any(Object),
            jobTitleRelative: expect.any(Object),
            relativeJobNumber: expect.any(Object),
            emailRelative: expect.any(Object),
            relativeExtensionNumber: expect.any(Object),
            relativeRelationship: expect.any(Object),
            generalAdministrationRelative: expect.any(Object),
            administrationRelative: expect.any(Object),
            disclosure: expect.any(Object),
          }),
        );
      });
    });

    describe('getRelatives', () => {
      it('should return NewRelatives for default Relatives initial value', () => {
        const formGroup = service.createRelativesFormGroup(sampleWithNewData);

        const relatives = service.getRelatives(formGroup) as any;

        expect(relatives).toMatchObject(sampleWithNewData);
      });

      it('should return NewRelatives for empty Relatives initial value', () => {
        const formGroup = service.createRelativesFormGroup();

        const relatives = service.getRelatives(formGroup) as any;

        expect(relatives).toMatchObject({});
      });

      it('should return IRelatives', () => {
        const formGroup = service.createRelativesFormGroup(sampleWithRequiredData);

        const relatives = service.getRelatives(formGroup) as any;

        expect(relatives).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRelatives should not enable id FormControl', () => {
        const formGroup = service.createRelativesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRelatives should disable id FormControl', () => {
        const formGroup = service.createRelativesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
