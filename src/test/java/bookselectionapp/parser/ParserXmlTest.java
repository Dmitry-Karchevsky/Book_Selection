package bookselectionapp.parser;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class ParserXmlTest {

    @Autowired
    ParserXml parserXml;

    //@Autowired
   //AuthorService authorService;

    @Test
    void parse() throws IOException {
        //parserXml.parseAll();

    }

    @Test
    void parseAll() {
    }

    @Test
    void addHrefsInOneFile() {
    }

    @Test
    void writeAuthorToDb() {
        parserXml.writeAuthorsToDb();
    }

    @Test
    void writeBooksOneAuthorInDb() {
        parserXml.writeBooksInDb();
    }
}