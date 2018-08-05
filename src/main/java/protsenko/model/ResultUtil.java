package protsenko.model;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

class ResultUtil {

    private ResultUtil() {
    }

    static String buildPath(Element element) {
        if (element == null) return "No result";

        List<String> list = element.parents()
                .stream()
                .map(ResultUtil::elementToString)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
            sb.append(" > ");
        }
        sb.append(elementToString(element));
        return sb.toString();
    }

    static String buildReasons(List<String> reasonList) {
        if (reasonList == null || reasonList.isEmpty()) return "No result";

        StringBuilder sb = new StringBuilder();
        for (String s : reasonList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String elementToString(Element e) {
        if (e.siblingElements().isEmpty())
            return String.format("%s", e.tagName());
        return String.format("%s[%s]", e.tagName(), e.elementSiblingIndex());
    }
}
