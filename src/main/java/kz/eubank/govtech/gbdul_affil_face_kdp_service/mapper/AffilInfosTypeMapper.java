package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.AffilInfosTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.AffilInfosType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FounderTypeMapper.class})
public interface AffilInfosTypeMapper {
    @Mapping(source = "BIN", target = "bin")
    @Mapping(source = "founders", target = "founders")
    AffilInfosTypeDTO toDto(AffilInfosType affilInfosType);
}
