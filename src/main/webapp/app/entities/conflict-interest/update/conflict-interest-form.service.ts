import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IConflictInterest, NewConflictInterest } from '../conflict-interest.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IConflictInterest for edit and NewConflictInterestFormGroupInput for create.
 */
type ConflictInterestFormGroupInput = IConflictInterest | PartialWithRequiredKeyOf<NewConflictInterest>;

type ConflictInterestFormDefaults = Pick<NewConflictInterest, 'id'>;

type ConflictInterestFormGroupContent = {
  id: FormControl<IConflictInterest['id'] | NewConflictInterest['id']>;
  conflictInterestClassification: FormControl<IConflictInterest['conflictInterestClassification']>;
  conflictInterestEntityIndividual: FormControl<IConflictInterest['conflictInterestEntityIndividual']>;
  conflictInterestDateArose: FormControl<IConflictInterest['conflictInterestDateArose']>;
  conflictInterestImpact: FormControl<IConflictInterest['conflictInterestImpact']>;
  relationshipEntityIndividual: FormControl<IConflictInterest['relationshipEntityIndividual']>;
  affectPermission: FormControl<IConflictInterest['affectPermission']>;
  caseDetails: FormControl<IConflictInterest['caseDetails']>;
  discloseInspector: FormControl<IConflictInterest['discloseInspector']>;
  facilityRegisteredInsuranceName: FormControl<IConflictInterest['facilityRegisteredInsuranceName']>;
  office: FormControl<IConflictInterest['office']>;
};

export type ConflictInterestFormGroup = FormGroup<ConflictInterestFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ConflictInterestFormService {
  createConflictInterestFormGroup(conflictInterest: ConflictInterestFormGroupInput = { id: null }): ConflictInterestFormGroup {
    const conflictInterestRawValue = {
      ...this.getFormDefaults(),
      ...conflictInterest,
    };
    return new FormGroup<ConflictInterestFormGroupContent>({
      id: new FormControl(
        { value: conflictInterestRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      conflictInterestClassification: new FormControl(conflictInterestRawValue.conflictInterestClassification),
      conflictInterestEntityIndividual: new FormControl(conflictInterestRawValue.conflictInterestEntityIndividual),
      conflictInterestDateArose: new FormControl(conflictInterestRawValue.conflictInterestDateArose),
      conflictInterestImpact: new FormControl(conflictInterestRawValue.conflictInterestImpact),
      relationshipEntityIndividual: new FormControl(conflictInterestRawValue.relationshipEntityIndividual),
      affectPermission: new FormControl(conflictInterestRawValue.affectPermission),
      caseDetails: new FormControl(conflictInterestRawValue.caseDetails),
      discloseInspector: new FormControl(conflictInterestRawValue.discloseInspector),
      facilityRegisteredInsuranceName: new FormControl(conflictInterestRawValue.facilityRegisteredInsuranceName),
      office: new FormControl(conflictInterestRawValue.office),
    });
  }

  getConflictInterest(form: ConflictInterestFormGroup): IConflictInterest | NewConflictInterest {
    return form.getRawValue() as IConflictInterest | NewConflictInterest;
  }

  resetForm(form: ConflictInterestFormGroup, conflictInterest: ConflictInterestFormGroupInput): void {
    const conflictInterestRawValue = { ...this.getFormDefaults(), ...conflictInterest };
    form.reset(
      {
        ...conflictInterestRawValue,
        id: { value: conflictInterestRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ConflictInterestFormDefaults {
    return {
      id: null,
    };
  }
}
