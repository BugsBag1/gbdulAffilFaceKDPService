package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderFLTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.FounderFLType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FounderFLTypeMapper {
    @Mapping(source = "IIN", target = "iin")
    FounderFLTypeDTO toDto(FounderFLType founderFLType);
}
