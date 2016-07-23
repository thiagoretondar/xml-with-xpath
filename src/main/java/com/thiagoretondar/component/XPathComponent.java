package com.thiagoretondar.component;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by thiagoretondar on 7/23/16.
 */
@Component
public class XPathComponent {

    private XPath xpath;

    public XPathComponent() {
        this.xpath = XPathFactory.newInstance().newXPath();
    }

    public NodeList getNodeListFrom(Document document, String expression) throws XPathExpressionException {
        return (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }
}
