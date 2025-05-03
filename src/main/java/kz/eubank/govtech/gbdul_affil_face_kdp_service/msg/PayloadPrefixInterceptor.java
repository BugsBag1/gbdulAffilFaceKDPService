package kz.eubank.govtech.gbdul_affil_face_kdp_service.msg;

import kz.govtech.m11s.syncshepclient.web.ws.client.interceptor.IShepClientInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;
import org.springframework.xml.transform.TransformerHelper;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Order(25000)
@Slf4j
public class PayloadPrefixInterceptor extends TransformerHelper implements IShepClientInterceptor {
    public static final String NAMESPACE = "";
    public static final String XMLNS = "xmlns";


    @SneakyThrows
    @Override
    public boolean handleRequest(final MessageContext messageContext) throws WebServiceClientException {
        WebServiceMessage request = messageContext.getRequest();
        Source payloadSource = request.getPayloadSource();
        DOMResult result = new DOMResult();
        transform(payloadSource, result);
        removePrefix(result.getNode());
        transform(new DOMSource(result.getNode()), request.getPayloadResult());
        return true;
    }

    private void removePrefix(Node node) {
        if (node == null) {
            return;
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            removeNamespaceDeclaration(node);
        }
        NodeList childNodes = node.getChildNodes();
        if (childNodes != null) {
            IntStream.of(0, childNodes.getLength()).forEach(index -> removePrefix(childNodes.item(index)));
        }
        Node nextSibling = node.getNextSibling();
        if (nextSibling != null) {
            removePrefix(nextSibling);
        }
    }

    private void removeNamespaceDeclaration(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        IntStream.range(0, attributes.getLength()).forEach(index -> {
            Node attribute = attributes.item(index);
            if (attribute != null && StringUtils.startsWith(attribute.getNodeName(), XMLNS) &&
                StringUtils.equals(attribute.getNodeValue(), NAMESPACE)) {
                attributes.removeNamedItemNS(attribute.getNamespaceURI(), attribute.getLocalName());
            }
        });
    }

    @Override
    public boolean handleResponse(final MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleFault(final MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public void afterCompletion(final MessageContext messageContext, final Exception e)
        throws WebServiceClientException {

    }
}
