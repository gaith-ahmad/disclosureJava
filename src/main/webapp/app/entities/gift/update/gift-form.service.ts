import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IGift, NewGift } from '../gift.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IGift for edit and NewGiftFormGroupInput for create.
 */
type GiftFormGroupInput = IGift | PartialWithRequiredKeyOf<NewGift>;

type GiftFormDefaults = Pick<NewGift, 'id'>;

type GiftFormGroupContent = {
  id: FormControl<IGift['id'] | NewGift['id']>;
  giftNameOrganizationGiven: FormControl<IGift['giftNameOrganizationGiven']>;
  giftReason: FormControl<IGift['giftReason']>;
  giftOfficialOccasion: FormControl<IGift['giftOfficialOccasion']>;
  giftSpoilsQuickly: FormControl<IGift['giftSpoilsQuickly']>;
  giftForPersonalUse: FormControl<IGift['giftForPersonalUse']>;
  giftType: FormControl<IGift['giftType']>;
  giftEstimatedValue: FormControl<IGift['giftEstimatedValue']>;
  giftDateReceiving: FormControl<IGift['giftDateReceiving']>;
  giftOwnDesire: FormControl<IGift['giftOwnDesire']>;
  giftImpact: FormControl<IGift['giftImpact']>;
  giftReasonAcceptanceRejection: FormControl<IGift['giftReasonAcceptanceRejection']>;
  amountCashOffered: FormControl<IGift['amountCashOffered']>;
  otherNotes: FormControl<IGift['otherNotes']>;
  formalOccasionVisit: FormControl<IGift['formalOccasionVisit']>;
};

export type GiftFormGroup = FormGroup<GiftFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class GiftFormService {
  createGiftFormGroup(gift: GiftFormGroupInput = { id: null }): GiftFormGroup {
    const giftRawValue = {
      ...this.getFormDefaults(),
      ...gift,
    };
    return new FormGroup<GiftFormGroupContent>({
      id: new FormControl(
        { value: giftRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      giftNameOrganizationGiven: new FormControl(giftRawValue.giftNameOrganizationGiven),
      giftReason: new FormControl(giftRawValue.giftReason),
      giftOfficialOccasion: new FormControl(giftRawValue.giftOfficialOccasion, {
        validators: [Validators.required],
      }),
      giftSpoilsQuickly: new FormControl(giftRawValue.giftSpoilsQuickly, {
        validators: [Validators.required],
      }),
      giftForPersonalUse: new FormControl(giftRawValue.giftForPersonalUse, {
        validators: [Validators.required],
      }),
      giftType: new FormControl(giftRawValue.giftType, {
        validators: [Validators.required],
      }),
      giftEstimatedValue: new FormControl(giftRawValue.giftEstimatedValue),
      giftDateReceiving: new FormControl(giftRawValue.giftDateReceiving, {
        validators: [Validators.required],
      }),
      giftOwnDesire: new FormControl(giftRawValue.giftOwnDesire, {
        validators: [Validators.required],
      }),
      giftImpact: new FormControl(giftRawValue.giftImpact, {
        validators: [Validators.required],
      }),
      giftReasonAcceptanceRejection: new FormControl(giftRawValue.giftReasonAcceptanceRejection),
      amountCashOffered: new FormControl(giftRawValue.amountCashOffered),
      otherNotes: new FormControl(giftRawValue.otherNotes),
      formalOccasionVisit: new FormControl(giftRawValue.formalOccasionVisit),
    });
  }

  getGift(form: GiftFormGroup): IGift | NewGift {
    return form.getRawValue() as IGift | NewGift;
  }

  resetForm(form: GiftFormGroup, gift: GiftFormGroupInput): void {
    const giftRawValue = { ...this.getFormDefaults(), ...gift };
    form.reset(
      {
        ...giftRawValue,
        id: { value: giftRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): GiftFormDefaults {
    return {
      id: null,
    };
  }
}
