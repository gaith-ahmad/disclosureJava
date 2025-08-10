import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../conflict-interest.test-samples';

import { ConflictInterestFormService } from './conflict-interest-form.service';

describe('ConflictInterest Form Service', () => {
  let service: ConflictInterestFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConflictInterestFormService);
  });

  describe('Service methods', () => {
    describe('createConflictInterestFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createConflictInterestFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            conflictInterestClassification: expect.any(Object),
            conflictInterestEntityIndividual: expect.any(Object),
            conflictInterestDateArose: expect.any(Object),
            conflictInterestImpact: expect.any(Object),
            relationshipEntityIndividual: expect.any(Object),
            affectPermission: expect.any(Object),
            caseDetails: expect.any(Object),
            discloseInspector: expect.any(Object),
            facilityRegisteredInsuranceName: expect.any(Object),
            office: expect.any(Object),
          }),
        );
      });

      it('passing IConflictInterest should create a new form with FormGroup', () => {
        const formGroup = service.createConflictInterestFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            conflictInterestClassification: expect.any(Object),
            conflictInterestEntityIndividual: expect.any(Object),
            conflictInterestDateArose: expect.any(Object),
            conflictInterestImpact: expect.any(Object),
            relationshipEntityIndividual: expect.any(Object),
            affectPermission: expect.any(Object),
            caseDetails: expect.any(Object),
            discloseInspector: expect.any(Object),
            facilityRegisteredInsuranceName: expect.any(Object),
            office: expect.any(Object),
          }),
        );
      });
    });

    describe('getConflictInterest', () => {
      it('should return NewConflictInterest for default ConflictInterest initial value', () => {
        const formGroup = service.createConflictInterestFormGroup(sampleWithNewData);

        const conflictInterest = service.getConflictInterest(formGroup) as any;

        expect(conflictInterest).toMatchObject(sampleWithNewData);
      });

      it('should return NewConflictInterest for empty ConflictInterest initial value', () => {
        const formGroup = service.createConflictInterestFormGroup();

        const conflictInterest = service.getConflictInterest(formGroup) as any;

        expect(conflictInterest).toMatchObject({});
      });

      it('should return IConflictInterest', () => {
        const formGroup = service.createConflictInterestFormGroup(sampleWithRequiredData);

        const conflictInterest = service.getConflictInterest(formGroup) as any;

        expect(conflictInterest).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IConflictInterest should not enable id FormControl', () => {
        const formGroup = service.createConflictInterestFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewConflictInterest should disable id FormControl', () => {
        const formGroup = service.createConflictInterestFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
