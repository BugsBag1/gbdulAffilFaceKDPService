package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.AffilInfosTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.DirectoryTypeDTO;
import lombok.Data;

@Data
public class ResponseDTO {
    @Schema(description = "Статус ответа")
    private DirectoryTypeDTO status;
    @Schema
    private AffilInfosTypeDTO affilInfos;

}
