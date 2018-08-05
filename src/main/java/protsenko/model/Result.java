package protsenko.model;

import org.jsoup.nodes.Element;

import java.util.List;

public class Result {

    private final Element element;
    private final List<String> reasonList;

    public Result(Element element, List<String> reasonList) {
        this.element = element;
        this.reasonList = reasonList;
    }

    @Override
    public String toString() {
        return ResultUtil.buildPath(this.element) + "\n" + ResultUtil.buildReasons(this.reasonList);
    }

    public static Result empty() {
        return new Result(null, null);
    }
}
