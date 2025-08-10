export interface IConflictInterest {
  id: number;
  conflictInterestClassification?: string | null;
  conflictInterestEntityIndividual?: string | null;
  conflictInterestDateArose?: string | null;
  conflictInterestImpact?: string | null;
  relationshipEntityIndividual?: string | null;
  affectPermission?: string | null;
  caseDetails?: string | null;
  discloseInspector?: string | null;
  facilityRegisteredInsuranceName?: string | null;
  office?: string | null;
}

export type NewConflictInterest = Omit<IConflictInterest, 'id'> & { id: null };
