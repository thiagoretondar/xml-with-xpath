package com.thiagoretondar.component;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static javax.xml.parsers.DocumentBuilderFactory.newInstance;

/**
 * Created by thiagoretondar on 7/23/16.
 */
@Component
public class DocumentComponent {

    private DocumentBuilder builder;

    public DocumentComponent() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = newInstance();
        this.builder = documentBuilderFactory.newDocumentBuilder();
    }

    public Document getDocumentFromUrl(String url) throws IOException, SAXException {
        return builder.parse(url);
    }
}
