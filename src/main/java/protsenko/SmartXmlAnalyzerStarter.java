package protsenko;

import protsenko.model.Result;

import java.io.File;
import java.util.Objects;

public class SmartXmlAnalyzerStarter {

    public static void main(String[] args) {
        String originFileName = args[0];
        String modifiedFileName = args[1];
        String originElementId = args[2];

        Objects.requireNonNull(originFileName);
        Objects.requireNonNull(modifiedFileName);
        Objects.requireNonNull(originElementId);

        SmartXmlAnalyzer analyzer = new SmartXmlAnalyzer(new File(originFileName), new File(modifiedFileName), originElementId);
        Result result = analyzer.analyze();
        System.out.println(result);
    }

}
