import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IManagerInfo, NewManagerInfo } from '../manager-info.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IManagerInfo for edit and NewManagerInfoFormGroupInput for create.
 */
type ManagerInfoFormGroupInput = IManagerInfo | PartialWithRequiredKeyOf<NewManagerInfo>;

type ManagerInfoFormDefaults = Pick<NewManagerInfo, 'id'>;

type ManagerInfoFormGroupContent = {
  id: FormControl<IManagerInfo['id'] | NewManagerInfo['id']>;
  emailDirectManager: FormControl<IManagerInfo['emailDirectManager']>;
  nameDirectManager: FormControl<IManagerInfo['nameDirectManager']>;
  jobNumberDirectManager: FormControl<IManagerInfo['jobNumberDirectManager']>;
  jobTitleDirectManager: FormControl<IManagerInfo['jobTitleDirectManager']>;
  extDirectManager: FormControl<IManagerInfo['extDirectManager']>;
};

export type ManagerInfoFormGroup = FormGroup<ManagerInfoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ManagerInfoFormService {
  createManagerInfoFormGroup(managerInfo: ManagerInfoFormGroupInput = { id: null }): ManagerInfoFormGroup {
    const managerInfoRawValue = {
      ...this.getFormDefaults(),
      ...managerInfo,
    };
    return new FormGroup<ManagerInfoFormGroupContent>({
      id: new FormControl(
        { value: managerInfoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      emailDirectManager: new FormControl(managerInfoRawValue.emailDirectManager, {
        validators: [Validators.required],
      }),
      nameDirectManager: new FormControl(managerInfoRawValue.nameDirectManager, {
        validators: [Validators.required],
      }),
      jobNumberDirectManager: new FormControl(managerInfoRawValue.jobNumberDirectManager, {
        validators: [Validators.required],
      }),
      jobTitleDirectManager: new FormControl(managerInfoRawValue.jobTitleDirectManager, {
        validators: [Validators.required],
      }),
      extDirectManager: new FormControl(managerInfoRawValue.extDirectManager, {
        validators: [Validators.required],
      }),
    });
  }

  getManagerInfo(form: ManagerInfoFormGroup): IManagerInfo | NewManagerInfo {
    return form.getRawValue() as IManagerInfo | NewManagerInfo;
  }

  resetForm(form: ManagerInfoFormGroup, managerInfo: ManagerInfoFormGroupInput): void {
    const managerInfoRawValue = { ...this.getFormDefaults(), ...managerInfo };
    form.reset(
      {
        ...managerInfoRawValue,
        id: { value: managerInfoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ManagerInfoFormDefaults {
    return {
      id: null,
    };
  }
}
