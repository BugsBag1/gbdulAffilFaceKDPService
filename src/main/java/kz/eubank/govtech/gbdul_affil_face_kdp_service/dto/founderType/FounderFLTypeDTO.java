package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FounderFLTypeDTO {
    @Schema(description = "IIN")
    private String iin;
    @Schema(description = "Фамилия")
    private String surName;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String middleName;
}
