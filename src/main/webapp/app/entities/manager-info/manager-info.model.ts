export interface IManagerInfo {
  id: number;
  emailDirectManager?: string | null;
  nameDirectManager?: string | null;
  jobNumberDirectManager?: string | null;
  jobTitleDirectManager?: string | null;
  extDirectManager?: string | null;
}

export type NewManagerInfo = Omit<IManagerInfo, 'id'> & { id: null };
