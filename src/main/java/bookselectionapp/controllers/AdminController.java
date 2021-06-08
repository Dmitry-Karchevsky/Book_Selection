package bookselectionapp.controllers;

import bookselectionapp.parser.ParserXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParserXml parserXml;

    @GetMapping
    public ResponseEntity updateContent() {
        try {
            parserXml.parseAll();
            parserXml.writeAuthorsToDb();
            parserXml.writeBooksInDb();
        } catch (IOException exception) {
            logger.info("ошибка считывания");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
