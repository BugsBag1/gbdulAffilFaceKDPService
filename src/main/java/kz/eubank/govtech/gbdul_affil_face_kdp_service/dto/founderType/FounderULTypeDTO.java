package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FounderULTypeDTO {
    @Schema(description = "БИН")
    private String BIN;
    @Schema(description = "Наименование организации на русском языке")
    private String organizationNameRu;
    @Schema(description = "Наименование организации на государственном языке")
    private String organizationNameKz;
}
