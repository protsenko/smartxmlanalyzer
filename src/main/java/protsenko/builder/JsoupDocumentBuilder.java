package protsenko.builder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import protsenko.exception.ElementNotFoundException;
import protsenko.exception.JsoupParserException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsoupDocumentBuilder {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private final Document doc;

    public JsoupDocumentBuilder(File file) {
        this.doc = parse(file);
    }

    private Document parse(File file) {
        try {
            return Jsoup.parse(file, DEFAULT_CHARSET);
        } catch (IOException e) {
            throw new JsoupParserException(e);
        }
    }

    public Element getElementById(String id) {
        Optional<Element> maybeElement = Optional.ofNullable(doc.getElementById(id));
        String msg = String.format("Element with id %s not found.", id);
        return maybeElement.orElseThrow(() -> new ElementNotFoundException(msg));
    }

    public Elements getElementByTag(String tag) {
        return doc.getElementsByTag(tag);
    }

}
