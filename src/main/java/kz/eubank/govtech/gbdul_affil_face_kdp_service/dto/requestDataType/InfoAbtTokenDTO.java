package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType;

import lombok.Data;

import java.util.List;

@Data
public class InfoAbtTokenDTO {
    private List<TokenInfoDTO> tokens;
}
