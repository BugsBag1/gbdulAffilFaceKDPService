package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.AffilInfosTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType.DirectoryTypeDTO;
import lombok.Data;

@Data
public class ResponseDTO {
    @Schema(description = "Статус ответа")
    private DirectoryTypeDTO status;
    @Schema(description = "Сведения об аффилиированных лицах")
    private AffilInfosTypeDTO affilInfos;

    public ResponseDTO(DirectoryTypeDTO status, AffilInfosTypeDTO affilInfos) {
        this.status = status;
        this.affilInfos = affilInfos;
    }

    public ResponseDTO() {
    }

    public DirectoryTypeDTO getStatus() {
        return status;
    }

    public void setStatus(DirectoryTypeDTO status) {
        this.status = status;
    }

    public AffilInfosTypeDTO getAffilInfos() {
        return affilInfos;
    }

    public void setAffilInfos(AffilInfosTypeDTO affilInfos) {
        this.affilInfos = affilInfos;
    }
}
