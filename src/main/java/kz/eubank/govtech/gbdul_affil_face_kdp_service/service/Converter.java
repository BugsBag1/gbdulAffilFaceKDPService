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
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.ObjectFactory;
import kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse.TokenInfo;
import kz.govtech.m11s.syncshepclient.dto.DataResponse;
import kz.govtech.m11s.syncshepclient.service.AConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class Converter extends AConverter<RequestDTO, RequestDataType, ResponseDataType, ResponseDTO> {
    private final AppConfigurator configurator;

    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;

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
        return requestMapper.map(requestDto);
    }

    @Override
    protected Element createDataElement(RequestDTO requestDTO) {
        RequestDataType mapped = this.convertToServiceRequest(requestDTO);
        return this.marshalRequest(mapped);
    }

    @Override
    protected JAXBElement<RequestDataType> createJaxbElement(RequestDataType request) {
        return new ObjectFactory().createRequest(request);
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
        System.out.println("response " + response);
        System.out.println("STATUS  === " + response.getStatus());
        return responseMapper.toResponseDTO(response);
    }

    public ResponseDTO processCreateResponse(DataResponse dataResponse) {
        if (dataResponse == null || dataResponse.getData() == null) return null;

        Element dataElement = dataResponse.getData();
        Optional<Element> firstElementOpt = findFirstElement(dataElement);

        if (firstElementOpt.isPresent()) {
            ResponseDataType parsed = extractResponseFromElement(firstElementOpt.get());
            return convertToApiResponse(parsed);
        } else {
            String textXml = extractTextContent(dataElement);
            if (textXml != null && !textXml.trim().isEmpty()) {
                ResponseDataType parsed = unmarshalFromXmlString(textXml);
                return convertToApiResponse(parsed);
            }
        }

        // fallback
        return createResponse(dataResponse);
    }

    private Optional<Element> findFirstElement(Element dataElement) {
        NodeList children = dataElement.getChildNodes();
        Element firstElement = null;

        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("CHILDREN = " + children.item(i).getLocalName());
                if (firstElement != null) {
                    return Optional.empty();
                }
                firstElement = (Element) children.item(i);
            }
        }
        System.out.println("FIRST ELEMENT = " + firstElement);
        return Optional.ofNullable(firstElement);
    }

    public ResponseDataType extractResponseFromElement(Element element) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(element), new StreamResult(writer));
            String responseXml = writer.toString();

            JAXBContext jaxbContext = JAXBContext.newInstance("kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            JAXBElement<ResponseDataType> jaxbElement = (JAXBElement<ResponseDataType>)
                unmarshaller.unmarshal(new StringReader(responseXml));

            return jaxbElement.getValue();

        } catch (Exception e) {
            log.error("Ошибка при разборе XML: ", e);
        }
        return null;
    }

    private String extractTextContent(Element element) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE) {
                return node.getTextContent();
            }
        }
        return null;
    }

    private ResponseDataType unmarshalFromXmlString(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("kz.eubank.govtech.gbdul_affil_face_kdp_service.xsd.RequestAndResponse");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            JAXBElement<ResponseDataType> jaxbElement = (JAXBElement<ResponseDataType>)
                unmarshaller.unmarshal(new StringReader(xml));

            return jaxbElement.getValue();

        } catch (Exception e) {
            log.error("Ошибка при разборе текстовой XML-строки: ", e);
            return null;
        }
    }
}
