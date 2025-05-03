package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DirectoryTypeDTO {
    @Schema(description = "код")
    private String Code;
    @Schema(description = "значение на русском языке")
    private String NameRu;
    @Schema(description = "значение нагосударственном языке")
    private String NameKz;
}
