package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.InfoAbtTokenDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class RequestDTO implements Serializable {
    private String requestorBIN;
    private String bin;
    private InfoAbtTokenDTO infoAbtToken;
}
