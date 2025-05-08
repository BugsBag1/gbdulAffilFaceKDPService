package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.DirectoryTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.DirectoryType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectoryTypeMapper {
    DirectoryTypeDTO toDto(DirectoryType directoryType);
}
