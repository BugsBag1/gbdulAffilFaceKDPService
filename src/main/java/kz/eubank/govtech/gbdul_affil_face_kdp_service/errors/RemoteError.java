package kz.eubank.govtech.gbdul_affil_face_kdp_service.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoteError {
    private String errorCode;
    private String errorMessage;
}
