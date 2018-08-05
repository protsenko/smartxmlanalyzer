package protsenko.scanner;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

public interface ElementScanner {

    void scan(Element originElement, Element possibleElement, Map<Element, List<String>> resultMap);

    default void addReason(Element possibleElement, Map<Element, List<String>> resultMap, String reason) {
        resultMap.get(possibleElement).add(reason);
    }
}
