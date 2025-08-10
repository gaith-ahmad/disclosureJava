export interface IGift {
  id: number;
  giftNameOrganizationGiven?: string | null;
  giftReason?: string | null;
  giftOfficialOccasion?: string | null;
  giftSpoilsQuickly?: string | null;
  giftForPersonalUse?: string | null;
  giftType?: string | null;
  giftEstimatedValue?: string | null;
  giftDateReceiving?: string | null;
  giftOwnDesire?: string | null;
  giftImpact?: string | null;
  giftReasonAcceptanceRejection?: string | null;
  amountCashOffered?: string | null;
  otherNotes?: string | null;
  formalOccasionVisit?: string | null;
}

export type NewGift = Omit<IGift, 'id'> & { id: null };
