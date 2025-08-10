import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IRelatives, NewRelatives } from '../relatives.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRelatives for edit and NewRelativesFormGroupInput for create.
 */
type RelativesFormGroupInput = IRelatives | PartialWithRequiredKeyOf<NewRelatives>;

type RelativesFormDefaults = Pick<NewRelatives, 'id'>;

type RelativesFormGroupContent = {
  id: FormControl<IRelatives['id'] | NewRelatives['id']>;
  nameDiscloser: FormControl<IRelatives['nameDiscloser']>;
  nameRelative: FormControl<IRelatives['nameRelative']>;
  jobTitleRelative: FormControl<IRelatives['jobTitleRelative']>;
  relativeJobNumber: FormControl<IRelatives['relativeJobNumber']>;
  emailRelative: FormControl<IRelatives['emailRelative']>;
  relativeExtensionNumber: FormControl<IRelatives['relativeExtensionNumber']>;
  relativeRelationship: FormControl<IRelatives['relativeRelationship']>;
  generalAdministrationRelative: FormControl<IRelatives['generalAdministrationRelative']>;
  administrationRelative: FormControl<IRelatives['administrationRelative']>;
  disclosure: FormControl<IRelatives['disclosure']>;
};

export type RelativesFormGroup = FormGroup<RelativesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RelativesFormService {
  createRelativesFormGroup(relatives: RelativesFormGroupInput = { id: null }): RelativesFormGroup {
    const relativesRawValue = {
      ...this.getFormDefaults(),
      ...relatives,
    };
    return new FormGroup<RelativesFormGroupContent>({
      id: new FormControl(
        { value: relativesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nameDiscloser: new FormControl(relativesRawValue.nameDiscloser, {
        validators: [Validators.required],
      }),
      nameRelative: new FormControl(relativesRawValue.nameRelative, {
        validators: [Validators.required],
      }),
      jobTitleRelative: new FormControl(relativesRawValue.jobTitleRelative, {
        validators: [Validators.required],
      }),
      relativeJobNumber: new FormControl(relativesRawValue.relativeJobNumber, {
        validators: [Validators.required],
      }),
      emailRelative: new FormControl(relativesRawValue.emailRelative, {
        validators: [Validators.required],
      }),
      relativeExtensionNumber: new FormControl(relativesRawValue.relativeExtensionNumber, {
        validators: [Validators.required],
      }),
      relativeRelationship: new FormControl(relativesRawValue.relativeRelationship),
      generalAdministrationRelative: new FormControl(relativesRawValue.generalAdministrationRelative, {
        validators: [Validators.required],
      }),
      administrationRelative: new FormControl(relativesRawValue.administrationRelative, {
        validators: [Validators.required],
      }),
      disclosure: new FormControl(relativesRawValue.disclosure),
    });
  }

  getRelatives(form: RelativesFormGroup): IRelatives | NewRelatives {
    return form.getRawValue() as IRelatives | NewRelatives;
  }

  resetForm(form: RelativesFormGroup, relatives: RelativesFormGroupInput): void {
    const relativesRawValue = { ...this.getFormDefaults(), ...relatives };
    form.reset(
      {
        ...relativesRawValue,
        id: { value: relativesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): RelativesFormDefaults {
    return {
      id: null,
    };
  }
}
