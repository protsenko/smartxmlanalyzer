package protsenko;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import protsenko.builder.JsoupDocumentBuilder;
import protsenko.model.Result;
import protsenko.scanner.ElementAttributeScanner;
import protsenko.scanner.ElementScanner;
import protsenko.scanner.ElementTextScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartXmlAnalyzer {

    private final File originFile;
    private final File modifiedFile;
    private final String originElementId;

    private final List<ElementScanner> scanners = Arrays.asList(
            new ElementTextScanner(),
            new ElementAttributeScanner());


    public SmartXmlAnalyzer(File originFile, File modifiedFile, String originElementId) {
        this.originFile = originFile;
        this.modifiedFile = modifiedFile;
        this.originElementId = originElementId;
    }

    public Result analyze() {
        JsoupDocumentBuilder originDocumentBuilder = new JsoupDocumentBuilder(originFile);
        Element originElement = originDocumentBuilder.getElementById(originElementId);

        JsoupDocumentBuilder modifiedDocumentBuilder = new JsoupDocumentBuilder(modifiedFile);
        Elements possibleElements = modifiedDocumentBuilder.getElementByTag(originElement.tagName());

        Map<Element, List<String>> resultMap = initResultMap(possibleElements);

        possibleElements.stream()
                .forEach(pElement -> applyScanning(originElement, pElement, resultMap));

        return calculateResult(resultMap);
    }

    private Map<Element, List<String>> initResultMap(Elements possibleElements) {
        Map<Element, List<String>> result = new HashMap<>();
        for (Element element : possibleElements)
            result.put(element, new ArrayList<>());
        return result;
    }

    private void applyScanning(Element origin, Element possible, Map<Element, List<String>> resultMap) {
        scanners.stream()
                .forEach(elementScanner -> elementScanner.scan(origin, possible, resultMap));
    }

    private Result calculateResult(Map<Element, List<String>> resultMap) {
        Element favoriteElement = null;
        int value = 0;
        for (Element e : resultMap.keySet()) {
            int tempValue = resultMap.get(e).size();
            if (tempValue > value) {
                favoriteElement = e;
                value = tempValue;
            }
        }

        if (value == 0)
            return Result.empty();

        return new Result(favoriteElement, resultMap.get(favoriteElement));
    }

}
