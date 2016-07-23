package com.thiagoretondar.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by thiagoretondar on 7/22/16.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/producer", method = GET, produces = APPLICATION_XML_VALUE)
    public byte[] producer() throws IOException {
        Resource resource = new ClassPathResource("test_file.xml");
        File file = resource.getFile();

        return Files.readAllBytes(Paths.get(file.toURI()));
    }

}
