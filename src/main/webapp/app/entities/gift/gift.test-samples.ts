import { IGift, NewGift } from './gift.model';

export const sampleWithRequiredData: IGift = {
  id: 19871,
  giftOfficialOccasion: 'daily amazing',
  giftSpoilsQuickly: 'failing gently hm',
  giftForPersonalUse: 'nor',
  giftType: 'battle',
  giftDateReceiving: 'ha',
  giftOwnDesire: 'ack march',
  giftImpact: 'along soliloquy as',
};

export const sampleWithPartialData: IGift = {
  id: 23613,
  giftNameOrganizationGiven: 'soon while',
  giftReason: 'wherever provided',
  giftOfficialOccasion: 'fiddle cuddly',
  giftSpoilsQuickly: 'even babyish supposing',
  giftForPersonalUse: 'than verify though',
  giftType: 'brave',
  giftEstimatedValue: 'imaginative',
  giftDateReceiving: 'who openly yet',
  giftOwnDesire: 'absent',
  giftImpact: 'however',
  giftReasonAcceptanceRejection: 'glass circa clueless',
  otherNotes: 'voluntarily',
};

export const sampleWithFullData: IGift = {
  id: 21577,
  giftNameOrganizationGiven: 'even boo',
  giftReason: 'round',
  giftOfficialOccasion: 'stall',
  giftSpoilsQuickly: 'and feline',
  giftForPersonalUse: 'gosh transom gadzooks',
  giftType: 'foolishly',
  giftEstimatedValue: 'furthermore notarize muddy',
  giftDateReceiving: 'gee',
  giftOwnDesire: 'conceptualize almighty gah',
  giftImpact: 'before obedience once',
  giftReasonAcceptanceRejection: 'descriptive furlough yahoo',
  amountCashOffered: 'unearth mmm',
  otherNotes: 'orange vice',
  formalOccasionVisit: 'mobilize fraudster',
};

export const sampleWithNewData: NewGift = {
  giftOfficialOccasion: 'coincide ick pfft',
  giftSpoilsQuickly: 'oof',
  giftForPersonalUse: 'efface testing inveigle',
  giftType: 'with',
  giftDateReceiving: 'noon',
  giftOwnDesire: 'properly filter aha',
  giftImpact: 'forenenst inasmuch',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
