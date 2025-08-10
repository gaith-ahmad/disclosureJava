import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../gift.test-samples';

import { GiftFormService } from './gift-form.service';

describe('Gift Form Service', () => {
  let service: GiftFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GiftFormService);
  });

  describe('Service methods', () => {
    describe('createGiftFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createGiftFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            giftNameOrganizationGiven: expect.any(Object),
            giftReason: expect.any(Object),
            giftOfficialOccasion: expect.any(Object),
            giftSpoilsQuickly: expect.any(Object),
            giftForPersonalUse: expect.any(Object),
            giftType: expect.any(Object),
            giftEstimatedValue: expect.any(Object),
            giftDateReceiving: expect.any(Object),
            giftOwnDesire: expect.any(Object),
            giftImpact: expect.any(Object),
            giftReasonAcceptanceRejection: expect.any(Object),
            amountCashOffered: expect.any(Object),
            otherNotes: expect.any(Object),
            formalOccasionVisit: expect.any(Object),
          }),
        );
      });

      it('passing IGift should create a new form with FormGroup', () => {
        const formGroup = service.createGiftFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            giftNameOrganizationGiven: expect.any(Object),
            giftReason: expect.any(Object),
            giftOfficialOccasion: expect.any(Object),
            giftSpoilsQuickly: expect.any(Object),
            giftForPersonalUse: expect.any(Object),
            giftType: expect.any(Object),
            giftEstimatedValue: expect.any(Object),
            giftDateReceiving: expect.any(Object),
            giftOwnDesire: expect.any(Object),
            giftImpact: expect.any(Object),
            giftReasonAcceptanceRejection: expect.any(Object),
            amountCashOffered: expect.any(Object),
            otherNotes: expect.any(Object),
            formalOccasionVisit: expect.any(Object),
          }),
        );
      });
    });

    describe('getGift', () => {
      it('should return NewGift for default Gift initial value', () => {
        const formGroup = service.createGiftFormGroup(sampleWithNewData);

        const gift = service.getGift(formGroup) as any;

        expect(gift).toMatchObject(sampleWithNewData);
      });

      it('should return NewGift for empty Gift initial value', () => {
        const formGroup = service.createGiftFormGroup();

        const gift = service.getGift(formGroup) as any;

        expect(gift).toMatchObject({});
      });

      it('should return IGift', () => {
        const formGroup = service.createGiftFormGroup(sampleWithRequiredData);

        const gift = service.getGift(formGroup) as any;

        expect(gift).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IGift should not enable id FormControl', () => {
        const formGroup = service.createGiftFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewGift should disable id FormControl', () => {
        const formGroup = service.createGiftFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
