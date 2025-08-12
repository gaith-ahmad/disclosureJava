package com.gosi.disclosure.service.mapper;

import com.gosi.disclosure.domain.ManagerInfo;
import com.gosi.disclosure.domain.UserInfo;
import com.gosi.disclosure.service.dto.ManagerInfoDTO;
import com.gosi.disclosure.service.dto.UserInfoDTO;

public class UserInfoMapper {

    public static UserInfoDTO toDto(UserInfo entity) {
        if (entity == null) {
            return null;
        }

        UserInfoDTO dto = new UserInfoDTO();
        
        dto.setLoginName(entity.getLoginName());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setKey(entity.getKey() != null ? entity.getKey().intValue() : null); // تحويل Long لـ Integer
        dto.setName(entity.getName());
        dto.setJob_title(entity.getJobTitle());
        dto.setJob_number(entity.getJobNumber());
        dto.setEXT(entity.getExt());
        dto.setPublic_Administration(entity.getPublicAdministration());
        dto.setAdministration(entity.getAdministration());
        dto.setRelative_relationship(entity.getRelativeRelationship());
        dto.setOffice(entity.getOffice());

        // تحويل ManagerInfo إذا موجود
        if (entity.getManagerinfo() != null) {
            dto.setManagerinfo(toManagerInfoDto(entity.getManagerinfo()));
        }

        return dto;
    }

    private static ManagerInfoDTO toManagerInfoDto(ManagerInfo entity) {
        if (entity == null) {
            return null;
        }
        ManagerInfoDTO dto = new ManagerInfoDTO();
        dto.setEmail_direct_manager(entity.getEmailDirectManager());
        dto.setName_direct_manager(entity.getNameDirectManager());
        dto.setJob_number_direct_manager(entity.getJobNumberDirectManager());
        dto.setJob_title_direct_manager(entity.getJobTitleDirectManager());
        dto.setEXT_direct_manager(entity.getExtDirectManager());
        return dto;
    }
}
