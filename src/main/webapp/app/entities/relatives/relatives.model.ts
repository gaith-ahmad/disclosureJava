import { IDisclosure } from 'app/entities/disclosure/disclosure.model';

export interface IRelatives {
  id: number;
  nameDiscloser?: string | null;
  nameRelative?: string | null;
  jobTitleRelative?: string | null;
  relativeJobNumber?: string | null;
  emailRelative?: string | null;
  relativeExtensionNumber?: string | null;
  relativeRelationship?: string | null;
  generalAdministrationRelative?: string | null;
  administrationRelative?: string | null;
  disclosure?: IDisclosure | null;
}

export type NewRelatives = Omit<IRelatives, 'id'> & { id: null };
