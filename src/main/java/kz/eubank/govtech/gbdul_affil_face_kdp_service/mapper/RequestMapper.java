package kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.RequestDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.InfoAbtTokenDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.TokenInfoDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.InfoAbtToken;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.RequestDataType;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.TokenInfo;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(source = "bin", target = "BIN")
    RequestDataType map(RequestDTO requestDto);

    InfoAbtToken mapInfoAbtToken(InfoAbtTokenDTO infoAbtTokenDTO);

    TokenInfo mapTokenInfo(TokenInfoDTO tokenInfoDTO);

    List<TokenInfo> mapTokenInfoList(List<TokenInfoDTO> tokenInfoDTOList);
}
