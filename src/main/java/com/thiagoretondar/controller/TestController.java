package com.thiagoretondar.controller;

import com.thiagoretondar.component.DocumentComponent;
import com.thiagoretondar.component.XPathComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by thiagoretondar on 7/22/16.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private DocumentComponent documentComponent;

    @Autowired
    private XPathComponent xpathComponent;

    @RequestMapping(value = "/producer", method = GET, produces = APPLICATION_XML_VALUE)
    public byte[] producer() throws IOException {
        Resource resource = new ClassPathResource("test_file.xml");
        File file = resource.getFile();

        return Files.readAllBytes(Paths.get(file.toURI()));
    }

    @RequestMapping(value = "/consumer", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Integer> consumer() throws Exception {

        String url = "http://localhost:8080/test/producer";
        String expression = "//testRoot/testParent//test[@t='1']/@id";

        Document xmlDocumentFromUrl = null;
        try {
            xmlDocumentFromUrl = documentComponent.getDocumentFromUrl(url);
        } catch (Exception e) {
            throw new Exception("Could not get XML from URL " + url);
        }

        NodeList nodeList;
        try {
            nodeList = xpathComponent.getNodeListFrom(xmlDocumentFromUrl, expression);
        } catch (XPathExpressionException e) {
            throw new Exception("Could not get NodeList using expression " + expression);
        }

        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            ids.add(valueOf(nodeList.item(i).getNodeValue()));
        }

        return ids;
    }

}
