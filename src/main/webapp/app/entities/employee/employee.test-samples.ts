import { IEmployee, NewEmployee } from './employee.model';

export const sampleWithRequiredData: IEmployee = {
  id: 8899,
  email: 'Raymond60@yahoo.com',
};

export const sampleWithPartialData: IEmployee = {
  id: 15010,
  displayName: 'bracelet gratefully make',
  email: 'Ignatius24@yahoo.com',
  title: 'broadcast pace who',
  telephoneNumber: 'postbox severe',
  localPhone: 'how',
  physicalDeliveryOfficeName: 'customise avow',
};

export const sampleWithFullData: IEmployee = {
  id: 19019,
  displayName: 'sharply questionably softly',
  email: 'Sunny74@yahoo.com',
  title: 'near anti curiously',
  telephoneNumber: 'duh yum mothball',
  localPhone: 'grubby',
  department: 'nice',
  physicalDeliveryOfficeName: 'famously duh',
  distinguishedName: 'encode pish',
  username: 'pale',
};

export const sampleWithNewData: NewEmployee = {
  email: 'Lillie.Conroy@gmail.com',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
