import { IManagerInfo } from 'app/entities/manager-info/manager-info.model';

export interface IUserInfo {
  id: number;
  loginName?: string | null;
  fullName?: string | null;
  email?: string | null;
  key?: number | null;
  name?: string | null;
  jobTitle?: string | null;
  jobNumber?: string | null;
  ext?: string | null;
  publicAdministration?: string | null;
  administration?: string | null;
  relativeRelationship?: string | null;
  office?: string | null;
  managerinfo?: IManagerInfo | null;
}

export type NewUserInfo = Omit<IUserInfo, 'id'> & { id: null };
