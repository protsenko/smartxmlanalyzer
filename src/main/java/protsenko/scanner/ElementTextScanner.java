package protsenko.scanner;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

public class ElementTextScanner implements ElementScanner {

    @Override
    public void scan(Element originElement, Element possibleElement, Map<Element, List<String>> resultMap) {
        String oText = originElement.text();
        String pText = possibleElement.text();
        if (!isTextPresent(oText) || !isTextPresent(pText))
            return;

        if (oText.equals(pText)) {
            String msg = String.format("Elements have the same name: %s", oText);
            addReason(possibleElement, resultMap, msg);
        }
    }

    private boolean isTextPresent(String text) {
         return (text == null || "".equals(text)) ? false:  true;
    }
}
