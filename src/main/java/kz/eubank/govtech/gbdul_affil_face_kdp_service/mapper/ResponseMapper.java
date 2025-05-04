package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.ResponseDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderFLTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderULTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.AffilInfosTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.DirectoryTypeDTO;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.FounderTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    @Mapping(source = "status", target = "status")
    @Mapping(source = "affilInfos", target = "affilInfos")
    ResponseDTO toResponseDTO(ResponseDataType responseData);

    DirectoryTypeDTO directoryTypeToDirectoryTypeDTO(DirectoryType directoryType);

    AffilInfosTypeDTO affilInfosTypeToAffilInfosTypeDTO(AffilInfosType affilInfosType);

    FounderTypeDTO founderTypeToFounderTypeDTO(FounderType founderType);

    FounderULTypeDTO founderULTypeToFounderULTypeDTO(FounderULType founderULType);

    FounderFLTypeDTO founderFLTypeToFounderFLTypeDTO(FounderFLType founderFLType);
}
