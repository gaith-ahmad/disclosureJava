import { IGift } from 'app/entities/gift/gift.model';
import { IConflictInterest } from 'app/entities/conflict-interest/conflict-interest.model';

export interface IDisclosure {
  id: number;
  purposeOfDisclosure?: string | null;
  creationDate?: string | null;
  name?: string | null;
  jobTitle?: string | null;
  jobNumber?: string | null;
  ext?: string | null;
  publicAdministration?: string | null;
  administration?: string | null;
  confirm?: string | null;
  email?: string | null;
  emailDirectManager?: string | null;
  nameDirectManager?: string | null;
  jobNumberDirectManager?: string | null;
  jobTitleDirectManager?: string | null;
  extDirectManager?: string | null;
  areThereRelatives?: boolean | null;
  file?: string | null;
  filename?: string | null;
  gift?: IGift | null;
  conflictInterest?: IConflictInterest | null;
}

export type NewDisclosure = Omit<IDisclosure, 'id'> & { id: null };
