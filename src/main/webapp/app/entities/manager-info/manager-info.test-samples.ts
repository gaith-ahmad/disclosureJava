import { IManagerInfo, NewManagerInfo } from './manager-info.model';

export const sampleWithRequiredData: IManagerInfo = {
  id: 24583,
  emailDirectManager: 'inquisitively jaggedly mortally',
  nameDirectManager: 'mobilise busily sans',
  jobNumberDirectManager: 'merge serenade',
  jobTitleDirectManager: 'qua knowledgeably huzzah',
  extDirectManager: 'cheerfully shyly',
};

export const sampleWithPartialData: IManagerInfo = {
  id: 103,
  emailDirectManager: 'worth rubbery convoke',
  nameDirectManager: 'mixed monocle pink',
  jobNumberDirectManager: 'courageously till',
  jobTitleDirectManager: 'reschedule uh-huh fairly',
  extDirectManager: 'where',
};

export const sampleWithFullData: IManagerInfo = {
  id: 27370,
  emailDirectManager: 'to oof afore',
  nameDirectManager: 'positively pfft given',
  jobNumberDirectManager: 'hype',
  jobTitleDirectManager: 'revere under',
  extDirectManager: 'enthusiastically amid very',
};

export const sampleWithNewData: NewManagerInfo = {
  emailDirectManager: 'correctly unabashedly',
  nameDirectManager: 'versus',
  jobNumberDirectManager: 'summer',
  jobTitleDirectManager: 'phew',
  extDirectManager: 'yawn deeply',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
