package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderULTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.FounderULType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FounderULTypeMapper {
    @Mapping(source = "BIN", target = "bin")
    FounderULTypeDTO toDto(FounderULType founderULType);
}
