//package sdet.reporting;
//
//import jdk.jfr.Description;
//import org.junit.jupiter.api.Test;
//
//import javax.print.attribute.standard.Severity;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Epic("Framework Hardening")
//@Feature("Reporting Insights")
//@Owner("SDET Trainer")
//public class W6D4ReportinginsightsTest {
//    @Test
//    @Story("Categories")
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("Categories split flaky,test and failures so each bucket has an owner.")
//    void categoriesPutSpecificFlakyRuleBeForGenericBuckets() throws IOException{
//        String categories= Files.readString(Path.of("src/test/resources/allure/categories.json"));
//        int flakyIndex=categories.indexOf("\"Flaky tests\"");
//        int testDefectIndex=categories.indexOf("\"Test Defects (broken)\"");
//        int productDefectIndex=categories.indexOf("\"Product defects\"");
//
//        assertTrue(flakyIndex>=0,"Flaky category must exist");
//        assertTrue(testDefectIndex>flakyIndex,"Specific flaky rule must run before generic broken bucket");
//        assertTrue(productDefectIndex>flakyIndex,"Specific flaky rule must run before generic failed bucket");
//        assertTrue(categories.contains("\"flaky\":true"));
//        assertTrue(categories.contains("timeout|state element|connection reset"));
//    }
//
//    @Test
//    void environmentTemplateCarriesRunContext() throws IOException{
//        List<String> lines=Files.readAllLines(Path.of("src/test/resources/allure/environment.properties"));
//
//        assertTrue(lines.stream().anyMatch(line->line.startsWith("Browser=")));
//        assertTrue(lines.stream().anyMatch(line->line.startsWith("BaseURL")));
//        assertTrue(lines.stream().anyMatch(line->line.startsWith("Build==")));
//        assertTrue(lines.stream().anyMatch(line->line.startsWith("OS=")));
//    }
//
//    @Test
//    @Story("Executive overview")
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("The leadership view is the Overview page once categories,history,severity")
//    void executiveViewNeedsFourSignals(){
//        List<String> signals=List.of("status","trend","category split","environment");
//        assertEquals(4,signals.size());
//        assertTrue(signals.contains("trend"));
//        assertTrue(signals.contains("category split"));
//    }
//}
