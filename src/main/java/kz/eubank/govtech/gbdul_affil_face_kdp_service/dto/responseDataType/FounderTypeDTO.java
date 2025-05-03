package kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.responseDataType;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderFLTypeDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.founderType.FounderULTypeDTO;
import lombok.Data;

import java.util.List;

@Data
public class FounderTypeDTO {
    @Schema(description = "Юрлицо в котором запрашиваемый ЮЛ является учредителем")
    private FounderULTypeDTO ul;
    @Schema(description = "учредитель -- физлицо запрашиваемого ЮЛ")
    private FounderFLTypeDTO founderFLFirst;
    @Schema(description = "учредитель -- юрлицо запрашиваемого ЮЛ")
    private FounderULTypeDTO founderULFirst;
    @Schema(description = "учредители юрлица -- FounderUL_first")
    private List<FounderULTypeDTO> founderUL;
    @Schema(description = "учредители физлица -- FounderUL_first")
    private List<FounderFLTypeDTO> founderFL;
}
