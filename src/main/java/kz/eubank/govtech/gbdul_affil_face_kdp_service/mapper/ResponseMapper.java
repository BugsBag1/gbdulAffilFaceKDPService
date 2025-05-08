package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.ResponseDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = {
    DirectoryTypeMapper.class,
    AffilInfosTypeMapper.class,
    FounderTypeMapper.class,
    FounderULTypeMapper.class,
    FounderFLTypeMapper.class
})
public interface ResponseMapper {

    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    @Mapping(source = "status", target = "status")
    @Mapping(source = "affilInfos", target = "affilInfos")
    ResponseDTO toResponseDTO(ResponseDataType responseData);

}
