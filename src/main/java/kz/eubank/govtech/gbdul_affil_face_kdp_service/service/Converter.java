package kz.eubank.govtech.gbdul_affil_face_kdp_service.service;

import kz.eubank.govtech.gbdul_affil_face_kdp_service.config.AppConfigurator;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.RequestDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.ResponseDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.dto.requestDataType.TokenInfoDTO;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper.RequestMapper;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.mapper.ResponseMapper;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.InfoAbtToken;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.RequestDataType;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.ResponseDataType;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.TokenInfo;
import kz.govtech.m11s.syncshepclient.service.AConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Converter extends AConverter<RequestDTO, RequestDataType, ResponseDataType, ResponseDTO> {
    private final AppConfigurator configurator;

    @Override
    protected String getServiceId() {
        return configurator.getShepServiceId();
    }

    @Override
    protected QName getRequestQName() {
        return new QName("request");
    }

    @Override
    protected Class<RequestDataType> getServiceRequestClass() {
        return RequestDataType.class;
    }

    @Override
    protected Class<ResponseDataType> getServiceResponseClass() {
        return ResponseDataType.class;
    }

    @Override
    protected Element signElement(Element element) {
        return element;
    }

    @Override
    protected RequestDataType convertToServiceRequest(RequestDTO requestDto) {

        RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
        return requestMapper.map(requestDto);
    }

    @Override
    protected Element transformDataElement(Document document) {
        Element element = document.getDocumentElement();
        if (!element.getTagName().equalsIgnoreCase("data")) {
            document.renameNode(element, "", "data");
        }

        return element;
    }

    @SneakyThrows
    @Override
    protected ResponseDTO convertToApiResponse(ResponseDataType response) {
        ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);
        return responseMapper.toResponseDTO(response);
    }
}
