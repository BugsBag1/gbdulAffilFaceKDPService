package kz.eubank.govtech.gbdul_affil_face_kdp_service.service;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.RequestDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.ResponseDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.InfoAbtTokenDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.TokenInfoDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.errors.Errors;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.KdpServiceClient;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.KdpServiceError;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.clients.kdp.dto.KdpToken;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.errors.RemoteError;
import kz.govtech.m11s.syncshepclient.dto.DataResponse;
import kz.govtech.m11s.syncshepclient.web.ws.client.SyncShepClient;
import kz.govtech.m11s.validation.errors.BadRequestException;
import kz.govtech.m11s.validation.errors.NotFoundException;
import kz.govtech.m11s.validation.errors.TerminalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final KdpServiceClient kdpServiceClient;
    private final Converter converter;
    private final SyncShepClient shepClient;
    private static final String SERVICE_ID = "QED_Bagalau_esepteri";
    private final static String STATUS_SUCCESS = "SCSS001";
    private final static String STATUS_NOT_FOUND = "404";

    public ResponseDTO getAffilFaceKDP(String requestorBIN, String bin){
        final KdpToken kdpToken;
        try {
            kdpToken = kdpServiceClient.getKdpToken(SERVICE_ID, requestorBIN, requestorBIN, requestorBIN);
        } catch (KdpServiceError.KdpNotFoundError e) {
            throw new BadRequestException("KDP token not found", Errors.KDP_TOKEN_NOT_FOUND);
        }

        // Сожаем токен
        InfoAbtTokenDTO infoAbtTokenDTO = new InfoAbtTokenDTO();
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
        List<TokenInfoDTO> tokenInfoDTOList = new ArrayList<>();
        tokenInfoDTO.setCode(kdpToken.getToken());
        tokenInfoDTO.setPublicKey(kdpToken.getPublicKey());
        tokenInfoDTOList.add(tokenInfoDTO);
        infoAbtTokenDTO.setTokens(tokenInfoDTOList);

        RequestDTO request = new RequestDTO();
        request.setBin(bin);
        request.setRequestorBIN(requestorBIN);
        request.setInfoAbtToken(infoAbtTokenDTO);

        final DataResponse dataResponse = shepClient.sendMessage(converter.createDataRequest(request));
        if (dataResponse.getStatusCode().equals(STATUS_NOT_FOUND)) {
            throw new NotFoundException(dataResponse.getStatusMessage());
        }

        if (!dataResponse.getStatusCode().equals(STATUS_SUCCESS)) {
            throw new TerminalException(
                dataResponse.getStatusMessage(),
                Errors.REMOTE_SERVICE_ERROR,
                Map.of(
                    "remoteServiceError",
                    new RemoteError(
                        dataResponse.getStatusCode(),
                        dataResponse.getStatusMessage()
                    )
                )
            );
        }

        return converter.createResponse(dataResponse);
    }
}
