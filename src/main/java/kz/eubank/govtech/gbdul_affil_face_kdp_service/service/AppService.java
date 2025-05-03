package kz.eubank.govtech.gbdul_affil_face_kdp_service.service;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.ResponseDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.errors.Errors;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.KdpServiceClient;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.KdpServiceError;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.dto.KdpToken;
import kz.govtech.m11s.validation.errors.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final KdpServiceClient kdpServiceClient;
    private static final String SERVICE_ID = "QED_Bagalau_esepteri";

    public ResponseDTO getAffilFaceKDP(String requestorBIN, String bin){
//        final KdpToken kdpToken;
//        try {
//            kdpToken = kdpServiceClient.getKdpToken(SERVICE_ID, iin, requestorClientId, requestorDepartmentName);
//        } catch (KdpServiceError.KdpNotFoundError e) {
//            throw new BadRequestException("KDP token not found", Errors.KDP_TOKEN_NOT_FOUND);
//        }
        return null;
    }
}
