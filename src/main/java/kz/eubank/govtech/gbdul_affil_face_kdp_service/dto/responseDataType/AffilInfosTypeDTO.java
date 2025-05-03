package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AffilInfosTypeDTO {
    @Schema(description = "БИН организации")
    private String bin;
    @Schema(description = "Полное наименование на русском языке")
    private String fullNameRu;
    @Schema(description = "Полное наименование на государственном языке")
    private String fullNameKz;
    @Schema(description = "Сведения об учредителях и где ЮЛ учредитель")
    private List<FounderTypeDTO> founders;
    @Schema(description = "Количество учредителей физлиц")
    private int countFlFounders;
    @Schema(description = "Количество учредителей юрлиц")
    private int countUlFounders;
}
