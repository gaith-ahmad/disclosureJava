export interface IEmployee {
  id: number;
  displayName?: string | null;
  email?: string | null;
  title?: string | null;
  telephoneNumber?: string | null;
  localPhone?: string | null;
  department?: string | null;
  physicalDeliveryOfficeName?: string | null;
  distinguishedName?: string | null;
  username?: string | null;
}

export type NewEmployee = Omit<IEmployee, 'id'> & { id: null };
