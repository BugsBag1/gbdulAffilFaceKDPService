package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.FounderTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.FounderType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FounderULTypeMapper.class, FounderFLTypeMapper.class})
public interface FounderTypeMapper {
    @Mapping(source = "UL", target = "ul")
    @Mapping(source = "founderFLFirst", target = "founderFLFirst")
    @Mapping(source = "founderULFirst", target = "founderULFirst")
    @Mapping(source = "foundersUL", target = "founderUL")
    @Mapping(source = "foundersFL", target = "founderFL")
    FounderTypeDTO toDto(FounderType founderType);
}
