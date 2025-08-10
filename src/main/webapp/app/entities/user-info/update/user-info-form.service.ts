import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IUserInfo, NewUserInfo } from '../user-info.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUserInfo for edit and NewUserInfoFormGroupInput for create.
 */
type UserInfoFormGroupInput = IUserInfo | PartialWithRequiredKeyOf<NewUserInfo>;

type UserInfoFormDefaults = Pick<NewUserInfo, 'id'>;

type UserInfoFormGroupContent = {
  id: FormControl<IUserInfo['id'] | NewUserInfo['id']>;
  loginName: FormControl<IUserInfo['loginName']>;
  fullName: FormControl<IUserInfo['fullName']>;
  email: FormControl<IUserInfo['email']>;
  key: FormControl<IUserInfo['key']>;
  name: FormControl<IUserInfo['name']>;
  jobTitle: FormControl<IUserInfo['jobTitle']>;
  jobNumber: FormControl<IUserInfo['jobNumber']>;
  ext: FormControl<IUserInfo['ext']>;
  publicAdministration: FormControl<IUserInfo['publicAdministration']>;
  administration: FormControl<IUserInfo['administration']>;
  relativeRelationship: FormControl<IUserInfo['relativeRelationship']>;
  office: FormControl<IUserInfo['office']>;
  managerinfo: FormControl<IUserInfo['managerinfo']>;
};

export type UserInfoFormGroup = FormGroup<UserInfoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UserInfoFormService {
  createUserInfoFormGroup(userInfo: UserInfoFormGroupInput = { id: null }): UserInfoFormGroup {
    const userInfoRawValue = {
      ...this.getFormDefaults(),
      ...userInfo,
    };
    return new FormGroup<UserInfoFormGroupContent>({
      id: new FormControl(
        { value: userInfoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      loginName: new FormControl(userInfoRawValue.loginName, {
        validators: [Validators.required],
      }),
      fullName: new FormControl(userInfoRawValue.fullName, {
        validators: [Validators.required],
      }),
      email: new FormControl(userInfoRawValue.email, {
        validators: [Validators.required],
      }),
      key: new FormControl(userInfoRawValue.key),
      name: new FormControl(userInfoRawValue.name, {
        validators: [Validators.required],
      }),
      jobTitle: new FormControl(userInfoRawValue.jobTitle, {
        validators: [Validators.required],
      }),
      jobNumber: new FormControl(userInfoRawValue.jobNumber, {
        validators: [Validators.required],
      }),
      ext: new FormControl(userInfoRawValue.ext, {
        validators: [Validators.required],
      }),
      publicAdministration: new FormControl(userInfoRawValue.publicAdministration, {
        validators: [Validators.required],
      }),
      administration: new FormControl(userInfoRawValue.administration, {
        validators: [Validators.required],
      }),
      relativeRelationship: new FormControl(userInfoRawValue.relativeRelationship),
      office: new FormControl(userInfoRawValue.office),
      managerinfo: new FormControl(userInfoRawValue.managerinfo),
    });
  }

  getUserInfo(form: UserInfoFormGroup): IUserInfo | NewUserInfo {
    return form.getRawValue() as IUserInfo | NewUserInfo;
  }

  resetForm(form: UserInfoFormGroup, userInfo: UserInfoFormGroupInput): void {
    const userInfoRawValue = { ...this.getFormDefaults(), ...userInfo };
    form.reset(
      {
        ...userInfoRawValue,
        id: { value: userInfoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): UserInfoFormDefaults {
    return {
      id: null,
    };
  }
}
