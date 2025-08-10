import { IConflictInterest, NewConflictInterest } from './conflict-interest.model';

export const sampleWithRequiredData: IConflictInterest = {
  id: 115,
};

export const sampleWithPartialData: IConflictInterest = {
  id: 22867,
  conflictInterestEntityIndividual: 'lost',
  conflictInterestImpact: 'yellow aha disbar',
  relationshipEntityIndividual: 'monthly',
  affectPermission: 'always',
  discloseInspector: 'minus corral',
};

export const sampleWithFullData: IConflictInterest = {
  id: 12235,
  conflictInterestClassification: 'an reluctantly tomography',
  conflictInterestEntityIndividual: 'nor',
  conflictInterestDateArose: 'hm ouch',
  conflictInterestImpact: 'eek meanwhile till',
  relationshipEntityIndividual: 'yuck',
  affectPermission: 'attest',
  caseDetails: 'huzzah thunderbolt boo',
  discloseInspector: 'uselessly zesty',
  facilityRegisteredInsuranceName: 'anenst',
  office: 'championship beside cornet',
};

export const sampleWithNewData: NewConflictInterest = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
