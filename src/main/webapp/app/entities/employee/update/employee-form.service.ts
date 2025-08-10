import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IEmployee, NewEmployee } from '../employee.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmployee for edit and NewEmployeeFormGroupInput for create.
 */
type EmployeeFormGroupInput = IEmployee | PartialWithRequiredKeyOf<NewEmployee>;

type EmployeeFormDefaults = Pick<NewEmployee, 'id'>;

type EmployeeFormGroupContent = {
  id: FormControl<IEmployee['id'] | NewEmployee['id']>;
  displayName: FormControl<IEmployee['displayName']>;
  email: FormControl<IEmployee['email']>;
  title: FormControl<IEmployee['title']>;
  telephoneNumber: FormControl<IEmployee['telephoneNumber']>;
  localPhone: FormControl<IEmployee['localPhone']>;
  department: FormControl<IEmployee['department']>;
  physicalDeliveryOfficeName: FormControl<IEmployee['physicalDeliveryOfficeName']>;
  distinguishedName: FormControl<IEmployee['distinguishedName']>;
  username: FormControl<IEmployee['username']>;
};

export type EmployeeFormGroup = FormGroup<EmployeeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmployeeFormService {
  createEmployeeFormGroup(employee: EmployeeFormGroupInput = { id: null }): EmployeeFormGroup {
    const employeeRawValue = {
      ...this.getFormDefaults(),
      ...employee,
    };
    return new FormGroup<EmployeeFormGroupContent>({
      id: new FormControl(
        { value: employeeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      displayName: new FormControl(employeeRawValue.displayName),
      email: new FormControl(employeeRawValue.email, {
        validators: [Validators.required],
      }),
      title: new FormControl(employeeRawValue.title),
      telephoneNumber: new FormControl(employeeRawValue.telephoneNumber),
      localPhone: new FormControl(employeeRawValue.localPhone),
      department: new FormControl(employeeRawValue.department),
      physicalDeliveryOfficeName: new FormControl(employeeRawValue.physicalDeliveryOfficeName),
      distinguishedName: new FormControl(employeeRawValue.distinguishedName),
      username: new FormControl(employeeRawValue.username),
    });
  }

  getEmployee(form: EmployeeFormGroup): IEmployee | NewEmployee {
    return form.getRawValue() as IEmployee | NewEmployee;
  }

  resetForm(form: EmployeeFormGroup, employee: EmployeeFormGroupInput): void {
    const employeeRawValue = { ...this.getFormDefaults(), ...employee };
    form.reset(
      {
        ...employeeRawValue,
        id: { value: employeeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EmployeeFormDefaults {
    return {
      id: null,
    };
  }
}
