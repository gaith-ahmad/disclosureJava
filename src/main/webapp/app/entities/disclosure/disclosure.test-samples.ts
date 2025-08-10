import { IDisclosure, NewDisclosure } from './disclosure.model';

export const sampleWithRequiredData: IDisclosure = {
  id: 28361,
  areThereRelatives: false,
};

export const sampleWithPartialData: IDisclosure = {
  id: 3650,
  jobNumber: 'insistent over',
  ext: 'defendant quintessential',
  administration: 'wee bah',
  confirm: 'woot following sonnet',
  email: 'Vance12@yahoo.com',
  areThereRelatives: false,
};

export const sampleWithFullData: IDisclosure = {
  id: 31994,
  purposeOfDisclosure: 'um tool',
  creationDate: 'gosh gee',
  name: 'quaintly ha',
  jobTitle: 'Future Accountability Planner',
  jobNumber: 'suburban',
  ext: 'meaty',
  publicAdministration: 'incidentally questioningly along',
  administration: 'SUV',
  confirm: 'meh',
  email: 'Isabella_Padberg27@yahoo.com',
  emailDirectManager: 'untimely',
  nameDirectManager: 'provided',
  jobNumberDirectManager: 'abscond',
  jobTitleDirectManager: 'once',
  extDirectManager: 'deprave consequently circa',
  areThereRelatives: true,
  file: 'wriggler',
  filename: 'woeful fortunately decriminalize',
};

export const sampleWithNewData: NewDisclosure = {
  areThereRelatives: false,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
