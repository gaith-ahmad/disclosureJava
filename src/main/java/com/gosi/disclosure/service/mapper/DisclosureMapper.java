package com.gosi.disclosure.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.gosi.disclosure.domain.ConflictInterest;
import com.gosi.disclosure.domain.Disclosure;
import com.gosi.disclosure.domain.Gift;
import com.gosi.disclosure.domain.Relatives;
import com.gosi.disclosure.service.dto.ConflictInterestDTO;
import com.gosi.disclosure.service.dto.DisclosureDTO;
import com.gosi.disclosure.service.dto.GiftDTO;
import com.gosi.disclosure.service.dto.RelativesDTO;



public class DisclosureMapper {

    // إذا عندك مappers منفصلة للـ Gift/ConflictInterest/Relatives يمكنك استخدامهم هنا
    // هنا أكتب تحويلات بسيطة inline كمثال

    public static DisclosureDTO toDto(Disclosure entity) {
        if (entity == null) {
            return null;
        }

        DisclosureDTO dto = new DisclosureDTO();
        dto.setId(entity.getId());
        // الحقول الأساسية
        dto.setPurposeOfDisclosure(entity.getPurposeOfDisclosure());
        dto.setCreationDate(entity.getCreationDate());
        dto.setName(entity.getName());
        dto.setJob_title(entity.getJobTitle());
        dto.setJob_number(entity.getJobNumber());
        dto.setEXT(entity.getExt());
        dto.setPublic_Administration(entity.getPublicAdministration());
        dto.setAdministration(entity.getAdministration());
        dto.setConfirm(entity.getConfirm());
        dto.setEmail(entity.getEmail());
        dto.setEmail_direct_manager(entity.getEmailDirectManager());
        dto.setName_direct_manager(entity.getNameDirectManager());
        dto.setJob_number_direct_manager(entity.getJobNumberDirectManager());
        dto.setJob_title_direct_manager(entity.getJobTitleDirectManager());
        dto.setEXT_direct_manager(entity.getExtDirectManager());

        dto.setAreThereRelatives(Boolean.TRUE.equals(entity.getAreThereRelatives()));
        dto.setFile(entity.getFile());
        dto.setFilename(entity.getFilename());

        // تحويل Gift (OneToOne)
        Gift gift = entity.getGift();
        if (gift != null) {
            dto.setGift(toGiftDto(gift));
        }

        // تحويل ConflictInterest (OneToOne)
        ConflictInterest ci = entity.getConflictInterest();
        if (ci != null) {
            dto.setConflictInterest(toConflictInterestDto(ci));
        }

        // تحويل Relatives (OneToMany) من Set إلى List
        if (entity.getRelatives() != null) {
            List<RelativesDTO> relativesDtos = entity.getRelatives().stream()
                .map(DisclosureMapper::toRelativesDto)
                .collect(Collectors.toList());
            dto.setRelatives(relativesDtos);
        }

        return dto;
    }

    private static GiftDTO toGiftDto(Gift gift) {
        if (gift == null) return null;
        GiftDTO d = new GiftDTO();
        d.setGift_Name_organization_given(gift.getGiftNameOrganizationGiven());
        d.setGift_Reason(gift.getGiftReason());
        d.setGift_official_occasion(gift.getGiftOfficialOccasion());
        d.setGift_spoils_quickly(gift.getGiftSpoilsQuickly());
        d.setGift_for_personal_use(gift.getGiftForPersonalUse());
        d.setGift_Type(gift.getGiftType());
        d.setGift_estimated_value(gift.getGiftEstimatedValue());
        d.setGift_date_receiving(gift.getGiftDateReceiving());
        d.setGift_own_desire(gift.getGiftOwnDesire());
        d.setGift_impact(gift.getGiftImpact());
        d.setGift_reason_acceptance_rejection(gift.getGiftReasonAcceptanceRejection());
        d.setAmount_cash_offered(gift.getAmountCashOffered());
        d.setOther_Notes(gift.getOtherNotes());
        d.setFormal_occasion_visit(gift.getFormalOccasionVisit());
        return d;
    }

    private static ConflictInterestDTO toConflictInterestDto(ConflictInterest ci) {
        if (ci == null) return null;
        ConflictInterestDTO d = new ConflictInterestDTO();
        d.setConflict_interest_Classification(ci.getConflictInterestClassification());
        d.setConflict_interest_entity_individual(ci.getConflictInterestEntityIndividual());
        d.setConflict_interest_date_arose(ci.getConflictInterestDateArose());
        d.setConflict_interest_impact(ci.getConflictInterestImpact());
        d.setRelationship_entity_individual(ci.getRelationshipEntityIndividual());
        d.setAffectPermission(ci.getAffectPermission());
        d.setCase_details(ci.getCaseDetails());
        d.setDisclose_inspector(ci.getDiscloseInspector());
        d.setFacility_registered_insurance_name(ci.getFacilityRegisteredInsuranceName());
        d.setOffice(ci.getOffice());
        return d;
    }

    private static RelativesDTO toRelativesDto(Relatives r) {
        if (r == null) return null;
        RelativesDTO d = new RelativesDTO();
        d.setName_discloser(r.getNameDiscloser());
        d.setName_relative(r.getNameRelative());
        d.setJob_title_relative(r.getJobTitleRelative());
        d.setRelative_job_number(r.getRelativeJobNumber());
        d.setEmail_relative(r.getEmailRelative());
        d.setRelative_extension_number(r.getRelativeExtensionNumber());
        d.setRelative_relationship(r.getRelativeRelationship());
        d.setGeneral_Administration_relative(r.getGeneralAdministrationRelative());
        d.setAdministration_relative(r.getAdministrationRelative());
        return d;
    }
    public static Disclosure toEntity(DisclosureDTO dto) {
        if (dto == null) {
            return null;
        }

        Disclosure entity = new Disclosure();

        // الحقول الأساسية
        entity.setPurposeOfDisclosure(dto.getPurposeOfDisclosure());
        entity.setCreationDate(dto.getCreationDate());
        entity.setName(dto.getName());
        entity.setJobTitle(dto.getJob_title());
        entity.setJobNumber(dto.getJob_number());
        entity.setExt(dto.getEXT());
        entity.setPublicAdministration(dto.getPublic_Administration());
        entity.setAdministration(dto.getAdministration());
        entity.setConfirm(dto.getConfirm());
        entity.setEmail(dto.getEmail());
        entity.setEmailDirectManager(dto.getEmail_direct_manager());
        entity.setNameDirectManager(dto.getName_direct_manager());
        entity.setJobNumberDirectManager(dto.getJob_number_direct_manager());
        entity.setJobTitleDirectManager(dto.getJob_title_direct_manager());
        entity.setExtDirectManager(dto.getEXT_direct_manager());

        entity.setAreThereRelatives(dto.getAreThereRelatives());
        entity.setFile(dto.getFile());
        entity.setFilename(dto.getFilename());

        // تحويل Gift (OneToOne)
        if (dto.getGift() != null) {
            entity.setGift(toGiftEntity(dto.getGift()));
        }

        // تحويل ConflictInterest (OneToOne)
        if (dto.getConflictInterest() != null) {
            entity.setConflictInterest(toConflictInterestEntity(dto.getConflictInterest()));
        }

        // تحويل Relatives (List إلى Set)
        if (dto.getRelatives() != null) {
            entity.setRelatives(dto.getRelatives().stream()
                .map(DisclosureMapper::toRelativesEntity)
                .collect(Collectors.toSet()));
        }

        return entity;
    }

    private static Gift toGiftEntity(GiftDTO dto) {
        if (dto == null) return null;
        Gift g = new Gift();
        g.setGiftNameOrganizationGiven(dto.getGift_Name_organization_given());
        g.setGiftReason(dto.getGift_Reason());
        g.setGiftOfficialOccasion(dto.getGift_official_occasion());
        g.setGiftSpoilsQuickly(dto.getGift_spoils_quickly());
        g.setGiftForPersonalUse(dto.getGift_for_personal_use());
        g.setGiftType(dto.getGift_Type());
        g.setGiftEstimatedValue(dto.getGift_estimated_value());
        g.setGiftDateReceiving(dto.getGift_date_receiving());
        g.setGiftOwnDesire(dto.getGift_own_desire());
        g.setGiftImpact(dto.getGift_impact());
        g.setGiftReasonAcceptanceRejection(dto.getGift_reason_acceptance_rejection());
        g.setAmountCashOffered(dto.getAmount_cash_offered());
        g.setOtherNotes(dto.getOther_Notes());
        g.setFormalOccasionVisit(dto.getFormal_occasion_visit());
        return g;
    }

    private static ConflictInterest toConflictInterestEntity(ConflictInterestDTO dto) {
        if (dto == null) return null;
        ConflictInterest ci = new ConflictInterest();
        ci.setConflictInterestClassification(dto.getConflict_interest_Classification());
        ci.setConflictInterestEntityIndividual(dto.getConflict_interest_entity_individual());
        ci.setConflictInterestDateArose(dto.getConflict_interest_date_arose());
        ci.setConflictInterestImpact(dto.getConflict_interest_impact());
        ci.setRelationshipEntityIndividual(dto.getRelationship_entity_individual());
        ci.setAffectPermission(dto.getAffectPermission());
        ci.setCaseDetails(dto.getCase_details());
        ci.setDiscloseInspector(dto.getDisclose_inspector());
        ci.setFacilityRegisteredInsuranceName(dto.getFacility_registered_insurance_name());
        ci.setOffice(dto.getOffice());
        return ci;
    }

    private static Relatives toRelativesEntity(RelativesDTO dto) {
        if (dto == null) return null;
        Relatives r = new Relatives();
        r.setNameDiscloser(dto.getName_discloser());
        r.setNameRelative(dto.getName_relative());
        r.setJobTitleRelative(dto.getJob_title_relative());
        r.setRelativeJobNumber(dto.getRelative_job_number());
        r.setEmailRelative(dto.getEmail_relative());
        r.setRelativeExtensionNumber(dto.getRelative_extension_number());
        r.setRelativeRelationship(dto.getRelative_relationship());
        r.setGeneralAdministrationRelative(dto.getGeneral_Administration_relative());
        r.setAdministrationRelative(dto.getAdministration_relative());
        return r;
    }

}
