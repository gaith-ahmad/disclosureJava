import { IRelatives, NewRelatives } from './relatives.model';

export const sampleWithRequiredData: IRelatives = {
  id: 19086,
  nameDiscloser: 'yearningly reproachfully gah',
  nameRelative: 'ready blah vice',
  jobTitleRelative: 'until',
  relativeJobNumber: 'ouch indelible platypus',
  emailRelative: 'atop',
  relativeExtensionNumber: 'blindly jagged dowse',
  generalAdministrationRelative: 'sarcastic instance more',
  administrationRelative: 'while',
};

export const sampleWithPartialData: IRelatives = {
  id: 14183,
  nameDiscloser: 'svelte',
  nameRelative: 'unless before',
  jobTitleRelative: 'yuck chasuble',
  relativeJobNumber: 'er',
  emailRelative: 'steeple toward',
  relativeExtensionNumber: 'if amidst boastfully',
  relativeRelationship: 'stuff delectable sedately',
  generalAdministrationRelative: 'before',
  administrationRelative: 'superficial',
};

export const sampleWithFullData: IRelatives = {
  id: 3870,
  nameDiscloser: 'mortar messy forgather',
  nameRelative: 'what pleasant provided',
  jobTitleRelative: 'emergent suffice blond',
  relativeJobNumber: 'of',
  emailRelative: 'consequently',
  relativeExtensionNumber: 'hollow wonderfully',
  relativeRelationship: 'carefully woot weakly',
  generalAdministrationRelative: 'apud extremely whole',
  administrationRelative: 'till enrage pretend',
};

export const sampleWithNewData: NewRelatives = {
  nameDiscloser: 'inasmuch ill-fated dead',
  nameRelative: 'coaxingly electronics',
  jobTitleRelative: 'handle fragrant engender',
  relativeJobNumber: 'sense concerning',
  emailRelative: 'save',
  relativeExtensionNumber: 'palatable peaceful',
  generalAdministrationRelative: 'of',
  administrationRelative: 'declaration brand',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
