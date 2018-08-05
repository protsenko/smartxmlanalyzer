package protsenko.scanner;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

public class ElementAttributeScanner implements ElementScanner {

    @Override
    public void scan(Element originElement, Element possibleElement, Map<Element, List<String>> resultMap) {
        Attributes pAttributes = possibleElement.attributes();

        originElement.attributes().asList().stream()
                .forEach(attr -> {
                    if (isContainsSameAttribute(attr, pAttributes)) {
                        String msg = String.format("The same attribute %s with value %s", attr.getKey(), attr.getValue());
                        addReason(possibleElement, resultMap, msg);
                    }
                });
    }


    private boolean isContainsSameAttribute(Attribute originAttribute, Attributes pAttributes) {
        String originAttributeKey = originAttribute.getKey();
        if (!pAttributes.hasKey(originAttributeKey))
            return false;

        String possibleAttributeValue = pAttributes.get(originAttributeKey);
        if (!possibleAttributeValue.equals(originAttribute.getValue()))
            return false;

        return true;

    }
}
