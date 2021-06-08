package bookselectionapp.parser;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.Book;
import bookselectionapp.services.AuthorService;
import bookselectionapp.services.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParserXml {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    private static final String READRATE_SITEMAP_XML_FILE = "https://readrate.com/sitemap.xml";
    private static final String AUTHORS_FILE = "src\\main\\infoFiles\\authorsHref.txt";
    private static final String BOOKS_FILE = "src\\main\\infoFiles\\booksHref.txt";

    /**
     * Добавить время и дату последенего обновления
     */
    public void parseAll() throws IOException {
        Document doc = Jsoup.connect(READRATE_SITEMAP_XML_FILE).get();
        Elements elements = doc.select("loc");
        List<String> arrayAuthorsHrefOnXML = elements.stream().filter(x -> x.text().matches(".*authors.*")).map(Element::text).collect(Collectors.toList());
        List<String> arrayBooksHrefOnXML = elements.stream().filter(x -> x.text().matches(".*books.*")).map(Element::text).collect(Collectors.toList());

        arrayAuthorsHrefOnXML.forEach(x -> addHrefsInOneFile(x, AUTHORS_FILE));
        arrayBooksHrefOnXML.forEach(x -> addHrefsInOneFile(x, BOOKS_FILE));
    }

    private void addHrefsInOneFile(String xmlFile, String pathFile) {
        try (FileWriter fileWriter = new FileWriter(pathFile, true)) {
            Document doc = Jsoup.connect(xmlFile).get();
            Elements elements = doc.select("loc");
            for (Element element : elements) {
                fileWriter.write(element.text());
                fileWriter.append("\n");
            }
            fileWriter.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String getInfoFromElements(Elements elementsInfo) {
        StringBuilder info = new StringBuilder();
        for (Element oneElementInfo : elementsInfo) {
            if (info.length() > 20000) {
                info.delete(19999, info.length());
                int lastDotIndex = info.lastIndexOf(".");
                info.delete(lastDotIndex, info.length());
            }
            info.append(oneElementInfo.text())
                    .append("\n\n");
        }
        return info.toString().trim();
    }

    public void writeAuthorsToDb() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AUTHORS_FILE))) {
            String link = reader.readLine();
            int i = 0;
            while (!link.isBlank() && i < 20) {
                Document doc = Jsoup.connect(link).get();
                Elements elementsName = doc.select("h1");
                Elements elementsInfo = doc.select("span[style]");
                Elements elementsImgLink = doc.select("img[title][class][src][alt][width]");

                String imgSrc = elementsImgLink.first().attr("src");

                Author newAuthor = new Author();
                newAuthor.setName(elementsName.first().text());
                newAuthor.setInfo(getInfoFromElements(elementsInfo));
                if (imgSrc.length() < 100)
                    newAuthor.setLinkImage(imgSrc);

                authorService.saveAuthor(newAuthor);
                link = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBooksInDb() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String link = reader.readLine();
            int i = 0;
            while (!link.isBlank() && i < 20) {
                Document doc = Jsoup.connect(link).get();
                Elements elementsName = doc.select("h1");
                Elements elementsInfo = doc.select("div.entity");
                Elements elementsAuthor = doc.select("li.contributor").select("a.text-yellow[href]");
                Elements elementsAgeYearCode = doc.select("ul.book-info").select("li");

                Book newBook = new Book();
                newBook.setName(elementsName.first().text());
                newBook.setInfo(elementsInfo.last().text());

                for (Element el : elementsAgeYearCode){
                    if (el.text().contains("+"))
                        newBook.setAgeCategory(el.text());
                    else if (el.text().length() == 4)
                        newBook.setYearPublication(Integer.valueOf(el.text()));
                }

                newBook.setAuthor(authorService.findAuthorByName(elementsAuthor.first().text()));

                bookService.saveBook(newBook);
                link = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
