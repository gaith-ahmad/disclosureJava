import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IDisclosure, NewDisclosure } from '../disclosure.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDisclosure for edit and NewDisclosureFormGroupInput for create.
 */
type DisclosureFormGroupInput = IDisclosure | PartialWithRequiredKeyOf<NewDisclosure>;

type DisclosureFormDefaults = Pick<NewDisclosure, 'id' | 'areThereRelatives'>;

type DisclosureFormGroupContent = {
  id: FormControl<IDisclosure['id'] | NewDisclosure['id']>;
  purposeOfDisclosure: FormControl<IDisclosure['purposeOfDisclosure']>;
  creationDate: FormControl<IDisclosure['creationDate']>;
  name: FormControl<IDisclosure['name']>;
  jobTitle: FormControl<IDisclosure['jobTitle']>;
  jobNumber: FormControl<IDisclosure['jobNumber']>;
  ext: FormControl<IDisclosure['ext']>;
  publicAdministration: FormControl<IDisclosure['publicAdministration']>;
  administration: FormControl<IDisclosure['administration']>;
  confirm: FormControl<IDisclosure['confirm']>;
  email: FormControl<IDisclosure['email']>;
  emailDirectManager: FormControl<IDisclosure['emailDirectManager']>;
  nameDirectManager: FormControl<IDisclosure['nameDirectManager']>;
  jobNumberDirectManager: FormControl<IDisclosure['jobNumberDirectManager']>;
  jobTitleDirectManager: FormControl<IDisclosure['jobTitleDirectManager']>;
  extDirectManager: FormControl<IDisclosure['extDirectManager']>;
  areThereRelatives: FormControl<IDisclosure['areThereRelatives']>;
  file: FormControl<IDisclosure['file']>;
  filename: FormControl<IDisclosure['filename']>;
  gift: FormControl<IDisclosure['gift']>;
  conflictInterest: FormControl<IDisclosure['conflictInterest']>;
};

export type DisclosureFormGroup = FormGroup<DisclosureFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DisclosureFormService {
  createDisclosureFormGroup(disclosure: DisclosureFormGroupInput = { id: null }): DisclosureFormGroup {
    const disclosureRawValue = {
      ...this.getFormDefaults(),
      ...disclosure,
    };
    return new FormGroup<DisclosureFormGroupContent>({
      id: new FormControl(
        { value: disclosureRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      purposeOfDisclosure: new FormControl(disclosureRawValue.purposeOfDisclosure),
      creationDate: new FormControl(disclosureRawValue.creationDate),
      name: new FormControl(disclosureRawValue.name),
      jobTitle: new FormControl(disclosureRawValue.jobTitle),
      jobNumber: new FormControl(disclosureRawValue.jobNumber),
      ext: new FormControl(disclosureRawValue.ext),
      publicAdministration: new FormControl(disclosureRawValue.publicAdministration),
      administration: new FormControl(disclosureRawValue.administration),
      confirm: new FormControl(disclosureRawValue.confirm),
      email: new FormControl(disclosureRawValue.email),
      emailDirectManager: new FormControl(disclosureRawValue.emailDirectManager),
      nameDirectManager: new FormControl(disclosureRawValue.nameDirectManager),
      jobNumberDirectManager: new FormControl(disclosureRawValue.jobNumberDirectManager),
      jobTitleDirectManager: new FormControl(disclosureRawValue.jobTitleDirectManager),
      extDirectManager: new FormControl(disclosureRawValue.extDirectManager),
      areThereRelatives: new FormControl(disclosureRawValue.areThereRelatives, {
        validators: [Validators.required],
      }),
      file: new FormControl(disclosureRawValue.file),
      filename: new FormControl(disclosureRawValue.filename),
      gift: new FormControl(disclosureRawValue.gift),
      conflictInterest: new FormControl(disclosureRawValue.conflictInterest),
    });
  }

  getDisclosure(form: DisclosureFormGroup): IDisclosure | NewDisclosure {
    return form.getRawValue() as IDisclosure | NewDisclosure;
  }

  resetForm(form: DisclosureFormGroup, disclosure: DisclosureFormGroupInput): void {
    const disclosureRawValue = { ...this.getFormDefaults(), ...disclosure };
    form.reset(
      {
        ...disclosureRawValue,
        id: { value: disclosureRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): DisclosureFormDefaults {
    return {
      id: null,
      areThereRelatives: false,
    };
  }
}
